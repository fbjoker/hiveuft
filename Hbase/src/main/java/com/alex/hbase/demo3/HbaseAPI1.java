package com.alex.hbase.demo3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

public class HbaseAPI1 {
    public static void main(String[] args) throws IOException {

        Configuration conf = HBaseConfiguration.create();

        Connection conn = ConnectionFactory.createConnection(conf);

        Admin admin = conn.getAdmin();

        TableName tableName = TableName.valueOf("alex:demo1");
        boolean b = admin.tableExists(tableName);

        if (!b) {
            System.out.println("表格不存在");

            HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
            HColumnDescriptor info = new HColumnDescriptor("info");
            hTableDescriptor.addFamily(info);

            admin.createTable(hTableDescriptor);

        }

        admin.close();
        conn.close();


    }
}
