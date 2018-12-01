package com.alex.hbase.demo3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

public class HbaseAPI2 {

    public static void main(String[] args) throws IOException {

        Configuration conf = HBaseConfiguration.create();


        Connection conn = ConnectionFactory.createConnection(conf);


        Admin admin = conn.getAdmin();
        TableName tableName = TableName.valueOf("alex:demo1");

        boolean b = admin.tableExists(tableName);

        if (b) {

            admin.disableTable(tableName);
            admin.deleteTable(tableName);
            System.out.println("删除成功");
        }

        admin.close();
        conn.close();

    }
}
