package com.alex.hbase.dao;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InBoxDao {
    public void createTbale(Connection conn) throws IOException {

        Admin admin = conn.getAdmin();
        TableName tableName = TableName.valueOf("alex:inbox");
        boolean b = admin.tableExists(tableName);

        if (b) {
            admin.disableTable(tableName);

            admin.deleteTable(tableName);
        }

        HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
        HColumnDescriptor hColumnDescriptor = new HColumnDescriptor("info");
        hTableDescriptor.addFamily(hColumnDescriptor);
        hColumnDescriptor.setMaxVersions(5);
        hColumnDescriptor.setMinVersions(5);

        admin.createTable(hTableDescriptor);


        admin.close();
    }

    public void insertDatas(Connection conn, List<String> fansid, String userid,String rowkey) throws IOException {

        TableName tableName = TableName.valueOf("alex:inbox");

        Table table = conn.getTable(tableName);
        List<Put> puts = new ArrayList<Put>();
        for (String s : fansid) {
        Put put = new Put(Bytes.toBytes(s));
            byte[] f = Bytes.toBytes("info");
            byte[] q = Bytes.toBytes("info");
            byte[] c = Bytes.toBytes(userid);



            put.addColumn(f,q,c);



        }

        table.put(puts);

        table.close();


    }

    public void insertAttendDatas(Connection conn, String fanUser, String starUser, List<String> weiborowkey) throws IOException {
        TableName tableName = TableName.valueOf("alex:inbox");
        Table table = conn.getTable(tableName);


        List<Put> puts=new ArrayList<Put>();

        for (String s : weiborowkey) {
            Put put = new Put(Bytes.toBytes(fanUser));
            put.addColumn(Bytes.toBytes("f"),Bytes.toBytes("f"),Bytes.toBytes("f"));
            puts.add(put);
        }

    }
}
