package com.alex.hbase.service;

import com.alex.hbase.dao.AdminDao;
import com.alex.hbase.dao.InBoxDao;
import com.alex.hbase.dao.RelationDao;
import com.alex.hbase.dao.WeiBoDao;
import org.apache.hadoop.hbase.client.Connection;

import java.io.IOException;
import java.util.List;

public class WeiBoService {

    ThreadLocal<Connection> connHolder = new ThreadLocal<Connection>();

    AdminDao hbaseDao = new AdminDao();

    private WeiBoDao weiBoDao = new WeiBoDao();
    private InBoxDao inBoxDao = new InBoxDao();
    private RelationDao relationDao = new RelationDao();

    public synchronized void start() throws IOException {


        Connection conn = connHolder.get();
        if (conn == null) {
            conn = hbaseDao.getCoonection();
            connHolder.set(conn);

        }
    }

    public void end() {
        Connection conn = connHolder.get();
        if (conn != null) {

            try {
                conn.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        connHolder.remove();
    }


    /**
     * 创建命名空间
     */
    public void creatNamespace() throws IOException {


        Connection conn = connHolder.get();
        boolean b = hbaseDao.hasNamespace(conn);

        if (!b) {
            hbaseDao.createNamespcae(conn);
        }


    }

    /**
     * 创建表
     */

    public void creatTable() throws IOException {


        Connection conn = connHolder.get();
       weiBoDao.createTbale(conn);
       relationDao.createTbale(conn);
       inBoxDao.createTbale(conn);
    }

    public void publishWeiBo(String  userid,long time,String content) throws IOException {
        Connection conn = connHolder.get();

        String rowkey = weiBoDao.insertData(conn, userid, time, content);

        List<String> fansuserIds=relationDao.getFansIds(conn,userid);

        inBoxDao.insertDatas(conn,fansuserIds,userid,rowkey);

    }

    public void attendUser(String fanUser, String starUser) throws IOException {
        Connection conn = connHolder.get();
        relationDao.insertAttendData(conn,fanUser,starUser);


        relationDao.insertFansData(conn,starUser,fanUser);

        List<String> weiborowkey=  weiBoDao.scanData(conn,starUser);

        if(!weiborowkey.isEmpty()){

        inBoxDao.insertAttendDatas(conn,fanUser,starUser,weiborowkey);
        }



    }
}
