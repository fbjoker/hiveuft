package com.alex.hbase.demo3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class HbaseAPI4 {
    public static void main(String[] args) throws IOException {


        Configuration conf = HBaseConfiguration.create();

        Connection conn = ConnectionFactory.createConnection(conf);

        TableName tableName = TableName.valueOf("alex:demo1");
        Table table = conn.getTable(tableName);

        for (int i=0;i<=20;i++){

            Delete delete = new Delete(Bytes.toBytes(i));
            table.delete(delete);
        }

        table.close();
        conn.close();
    }
}
