package com.alex.hbase.demo3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HbaseAPI3 {
    public static void main(String[] args) throws IOException {

        Configuration conf = HBaseConfiguration.create();

        Connection conn = ConnectionFactory.createConnection(conf);

        TableName tableName = TableName.valueOf("alex:demo1");
        Table table = conn.getTable(tableName);


//        byte[] rowkey = Bytes.toBytes("1001");
//        byte[] family = Bytes.toBytes("info");
//        byte[] column = Bytes.toBytes("name");
//        byte[] val = Bytes.toBytes("alex");


        List<Put> puts = new ArrayList<Put>();
        for (int i=21;i<=200;i++){

            Put put = new Put(Bytes.toBytes(String.valueOf(i)));
           // System.out.println(Bytes.toBytes(i).toString());
            put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes("alex"+i));
            puts.add(put);

        }

        table.put(puts);



//        put.addColumn(family,column,val);
//        table.put(put);



        table.close();
        conn.close();


    }
}
