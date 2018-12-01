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

public class WeiBoDao {
    public void createTbale(Connection conn) throws IOException {


        Admin admin = conn.getAdmin();
        TableName tableName = TableName.valueOf("alex:weibo");
        boolean b = admin.tableExists(tableName);

        if (b) {
            admin.disableTable(tableName);

            admin.deleteTable(tableName);
        }

        HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
        HColumnDescriptor hColumnDescriptor = new HColumnDescriptor("info");
        hTableDescriptor.addFamily(hColumnDescriptor);
        admin.createTable(hTableDescriptor);


        admin.close();

    }

    public String insertData(Connection conn, String userid, long time, String content) throws IOException {
        TableName tableName = TableName.valueOf("alex:weibo");
        Table table = conn.getTable(tableName);
        String rowkey=userid+"_"+(Long.MAX_VALUE-time);

        Put put = new Put(Bytes.toBytes(rowkey));
        byte[] family = Bytes.toBytes("info");
        byte[] q = Bytes.toBytes("content");
        byte[] c = Bytes.toBytes(content);

        put.addColumn(family,q,c);

        table.put(put);


        table.close();

        return rowkey;

    }
}
