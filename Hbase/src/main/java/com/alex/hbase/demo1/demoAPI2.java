package com.alex.hbase.demo1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;


public class demoAPI2 {
    //配置文件
    public  static Configuration conf;



    public static void main(String[] args) throws Exception {
        //创建配置对象
        conf = HBaseConfiguration.create();



        //创建连接对象,根据配置信息
        Connection conn = ConnectionFactory.createConnection(conf);

        //获取表对象
        TableName tableName = TableName.valueOf("student");
        Table table = conn.getTable(tableName);

        //rowkey
        String rowkey="1004";
        byte[] browkey = Bytes.toBytes(rowkey);
        //rowkey.getBytes()
        Put put = new Put(browkey);
        //val
        put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("name"),Bytes.toBytes("kaka"));
        put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("age"),Bytes.toBytes("18"));
        put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("email"),1543384307000L,Bytes.toBytes("xxx@qq.com"));

        table.put(put);


        System.out.println("添加成功");
        Thread.sleep(5000);

        //删除数据
//
//        Delete del = new Delete(browkey);
//        table.delete(del);
//        System.out.println("删除成功");


        Scan scan = new Scan();


        table.close();
        conn.close();


    }


}
