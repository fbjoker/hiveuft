package com.alex.source.source2;


import org.apache.flume.Context;
import org.apache.flume.conf.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SQLSourceHelper {

    private static final Logger LOG = LoggerFactory.getLogger(SQLSourceHelper.class);
    private int runQueryDelay, //两次查询的时间间隔
            startFrom,            //开始id
            currentIndex,	     //当前id
            recordSixe = 0,      //每次查询返回结果的条数
            maxRow;                //每次查询的最大条数


    private String table,       //要操作的表
            columnsToSelect,     //用户传入的查询的列
            customQuery,          //用户传入的查询语句
            query,                 //构建的查询语句
            defaultCharsetResultSet;//编码集

    //上下文，用来获取配置文件
    private Context context;

    //为定义的变量赋值（默认值），可在flume任务的配置文件中修改
    private static final int DEFAULT_QUERY_DELAY = 10000;
    private static final int DEFAULT_START_VALUE = 0;
    private static final int DEFAULT_MAX_ROWS = 2000;
    private static final String DEFAULT_COLUMNS_SELECT = "*";
    private static final String DEFAULT_CHARSET_RESULTSET = "UTF-8";

    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static String connectionURL, connectionUserName, connectionPassword;


    static {
        Properties properties = new Properties();
        try {
            properties.load(SQLSourceHelper.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            String connectionUserName = properties.getProperty("dbUser");
            String connectionURL = properties.getProperty("dbUrl");
            String connectionPassword = properties.getProperty("dbPassword");

            Class.forName(properties.getProperty("dbDriver"));

        } catch (Exception e) {
            LOG.error(e.toString());
        }

    }

    //获取连接

     private static Connection getConn(String url,String username,String password)  {
         try {
             Connection conn = DriverManager.getConnection(url, username, password);
             return conn;
         } catch (SQLException e) {
             e.printStackTrace();
         }


         return null;
     }

    public SQLSourceHelper(Context context) {
        this.context = context;


        //有默认值参数：获取flume任务配置文件中的参数，读不到的采用默认值
        this.columnsToSelect = context.getString("columns.to.select", DEFAULT_COLUMNS_SELECT);
        this.runQueryDelay = context.getInteger("run.query.delay", DEFAULT_QUERY_DELAY);
        this.startFrom = context.getInteger("start.from", DEFAULT_START_VALUE);
        this.defaultCharsetResultSet = context.getString("default.charset.resultset", DEFAULT_CHARSET_RESULTSET);

        //无默认值参数：获取flume任务配置文件中的参数
        this.table = context.getString("table");
        this.customQuery = context.getString("custom.query");
        connectionURL = context.getString("connection.url");
        connectionUserName = context.getString("connection.user");
        connectionPassword = context.getString("connection.password");
        conn = getConn(connectionURL, connectionUserName, connectionPassword);

        //校验相应的配置信息，如果没有默认值的参数也没赋值，抛出异常
        checkMandatoryProperties();
        //获取当前的id
        currentIndex = getStatusDBIndex(startFrom);
        //构建查询语句
        query = buildQuery();
    }




    //校验相应的配置信息（表，查询语句以及数据库连接的参数）
    private void checkMandatoryProperties() {
        if (table == null) {
            throw new ConfigurationException("property table not set");
        }
        if (connectionURL == null) {
            throw new ConfigurationException("connection.url property not set");
        }
        if (connectionUserName == null) {
            throw new ConfigurationException("connection.user property not set");
        }
        if (connectionPassword == null) {
            throw new ConfigurationException("connection.password property not set");
        }
    }





    //生成要查询的sql语句如果用户有定义就用用户定义的,如果用户没有定义就构建一个
    private String buildQuery() {
        String sql="";
        currentIndex = getStatusDBIndex(startFrom);
        if(connectionURL!=null){

            sql=connectionURL;
        }else {
            sql = "SELECT " + columnsToSelect + " FROM " + table;

        }

        StringBuilder sb = new StringBuilder(sql);

        if(sql.contains("where")){
           sb.append( sql.substring(0,sql.length()-String.valueOf(currentIndex).length())+currentIndex);
           return sb.toString();

        }else {
            sb.append(sql).append("where id > ").append(currentIndex);
            return sb.toString();
        }



    }
    //执行要查询的sql语句
    List<List<Object>> executeQuery(){
         customQuery = buildQuery();
        List<List<Object>> lists= new ArrayList<List<Object>>();
        try {
            ps=conn.prepareStatement(customQuery);
            ResultSet resultSet = ps.executeQuery(customQuery);

            while (resultSet.next()){

                List<Object> list = new ArrayList<Object>();
                for (int i=0;i<resultSet.getMetaData().getColumnCount();i++){
                    list.add(resultSet.getObject(i));
                }
                lists.add(list);


            }
            return lists;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //把返回值转换为字符串的list
    List<String> getAllRows(List<List<Object>> queryResult) {
        List<String>  allRows = new ArrayList<String>();
        for (List<Object> objects : queryResult) {
            StringBuilder sb = new StringBuilder();
            for (Object object : objects) {
                sb.append(object.toString());

            }
            allRows.add(sb.toString());

        }

        return null;

    }

    //保存当前的offset到meta表里面
    //执行保存offset的sql
    //查询当前的offset值
    private int getStatusDBIndex(int startFrom) {
        return 0;
    }
    //执行查询offset的sql


    int getCurrentIndex() {
        return currentIndex;
    }

    void setCurrentIndex(int newValue) {
        currentIndex = newValue;
    }

    int getRunQueryDelay() {
        return runQueryDelay;
    }

    String getQuery() {
        return query;
    }

    String getConnectionURL() {
        return connectionURL;
    }

    private boolean isCustomQuerySet() {
        return (customQuery != null);
    }

    Context getContext() {
        return context;
    }

    public String getConnectionUserName() {
        return connectionUserName;
    }

    public String getConnectionPassword() {
        return connectionPassword;
    }

    String getDefaultCharsetResultSet() {
        return defaultCharsetResultSet;
    }
}
