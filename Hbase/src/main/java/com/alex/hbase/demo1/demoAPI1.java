package com.alex.hbase.demo1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;


public class demoAPI1 {
    //配置文件
    public  static Configuration conf;



    public static void main(String[] args) throws Exception {
        //创建配置对象
        conf = HBaseConfiguration.create();



        //创建连接对象,根据配置信息
        Connection conn = ConnectionFactory.createConnection(conf);
        //获取管理对象
        Admin admin = conn.getAdmin();

        TableName tableName=TableName.valueOf("emp");
        boolean flag = admin.tableExists(tableName);

        System.out.println("表格是否存在"+flag);
        if(!flag){
            HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
            HColumnDescriptor info = new HColumnDescriptor("info");

            hTableDescriptor.addFamily(info);
            admin.createTable(hTableDescriptor);
            System.out.println("创建成功");


        }else{

            admin.disableTable(tableName);

            admin.deleteTable(tableName);
            System.out.println("删除成功");


        }

        admin.close();
        conn.close();


    }


}
