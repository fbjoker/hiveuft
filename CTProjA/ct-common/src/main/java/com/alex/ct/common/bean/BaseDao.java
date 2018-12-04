package com.alex.ct.common.bean;

import com.alex.ct.common.api.Column;
import com.alex.ct.common.api.RowKey;
import com.alex.ct.common.api.TableRef;
import com.alex.ct.common.constant.Names;
import com.alex.ct.common.constant.ValConstant;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao {


    private ThreadLocal<Connection> connHolder= new ThreadLocal<Connection>();
    private ThreadLocal<Admin> adminHolder= new ThreadLocal<Admin>();



    protected  void start() throws IOException {

        getConnection();
        getAdmin();
    }


    protected  void  end() throws IOException {
        Connection conn = getConnection();
        Admin admin = getAdmin();
        if(conn!=null){
            conn.close();
            connHolder.remove();
        }
        if(admin!=null){
            admin.close();
            connHolder.remove();
        }



    }

    //连接对象,只有子类能够使用
    protected synchronized Connection getConnection() throws IOException {
        Connection conn = connHolder.get();
        if(conn==null){

            Configuration conf = HBaseConfiguration.create();
             conn = ConnectionFactory.createConnection(conf);
             connHolder.set(conn);
        }

        return conn;



    }

    //连接对象,只有子类能够使用
    protected synchronized Admin getAdmin() throws IOException {
        Admin admin = adminHolder.get();
        if(admin==null){

            admin= getConnection().getAdmin();
            adminHolder.set(admin);
        }

        return admin;


    }


    /**
     * 创建命名空间
     *
     * @param namespace
     */
    protected void creatNamespaceNX(String namespace) throws Exception {
        Admin admin = getAdmin();
        try {
            admin.getNamespaceDescriptor(namespace);
        } catch (NamespaceNotFoundException e) {
            e.printStackTrace();
            NamespaceDescriptor nd=  NamespaceDescriptor.create(namespace).build();

            admin.createNamespace(nd);

        }

    }

    protected void creatTableXX(String name, Integer regionCount, String... family) throws Exception {

        Admin admin = getAdmin();
        TableName tableName = TableName.valueOf(name);
        boolean b = admin.tableExists(tableName);

        if(b){
           admin.disableTable(tableName);
           admin.deleteTable(tableName);

        }

        creatTable(name,regionCount,family);



    }

    protected  void creatTable(String name, Integer regionCount, String... family) throws  Exception{
        Admin admin = adminHolder.get();

        TableName tableName = TableName.valueOf(name);
        HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);

        if(family==null||family.length==0){
            family=new String[1];
            family[0]= Names.CF_INFO.getvalue();
        }


        for (String s : family) {
            HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(s);
            hTableDescriptor.addFamily(hColumnDescriptor);
        }

        if(regionCount==null||regionCount<=1){

            admin.createTable(hTableDescriptor);

        }else {

            byte[][] splitkey=getSplitKeys(regionCount);
            admin.createTable(hTableDescriptor,splitkey);

        }





    }

    protected  byte[][] getSplitKeys(Integer regionCount){

        int split= regionCount-1;
        byte[][] bs= new byte[split][];
        List<byte[]> list= new ArrayList<byte[]>();

        for (int i = 0; i < split; i++) {

            list.add(Bytes.toBytes(i+"|"));

        }
        list.toArray(bs);




        return bs;

    }

    protected int getRengionNum(String tel, String date) {
        String usercode = tel.substring(tel.length() - 4);
        String yearcode = date.substring(0, 6);


        int i = usercode.hashCode();
        int j = yearcode.hashCode();

        int crc = Math.abs(i ^ j);

        int regionNum= crc% ValConstant.REGION_COUNT;
        return regionNum;

    }

    protected void putData(String name, Put put) throws IOException {

        Connection conn = getConnection();

        TableName tableName = TableName.valueOf(name);
        Table table = conn.getTable(tableName);

        table.put(put);


        table.close();


    }

    protected void putData(Object obj) throws Exception {
        Class clazz = obj.getClass();

        //获取注释
        TableRef tableRef = (TableRef) clazz.getAnnotation(TableRef.class);

        String tableName= tableRef.value();

        Field[] fields = clazz.getDeclaredFields();
        String stringrowkey="";
        for (Field field : fields) {
            RowKey rowk = field.getAnnotation(RowKey.class);
            if(rowk!=null){
                field.setAccessible(true);
                stringrowkey= (String) field.get(obj);
                break;
            }


            Connection conn = getConnection();

            Table table = conn.getTable(TableName.valueOf(tableName));

            Put put = new Put(Bytes.toBytes(stringrowkey));

            for (Field f : fields) {
                Column colume = f.getAnnotation(Column.class);
                if(colume!=null){
                    String family = colume.family();
                    String colume1 = colume.colume();
                    if(colume1==null||"".equals(colume1)){
                        colume1=f.getName();
                    }
                    f.setAccessible(true);
                    String value = (String) f.get(obj);
                put.addColumn(Bytes.toBytes(family),Bytes.toBytes(colume1),Bytes.toBytes(value));

                }


            }

            table.put(put);
            table.close();



        }


    }





}
