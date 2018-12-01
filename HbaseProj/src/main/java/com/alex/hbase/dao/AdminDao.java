package com.alex.hbase.dao;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.NamespaceNotFoundException;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

public class AdminDao {


    public Connection getCoonection() throws IOException {

        Configuration conf = HBaseConfiguration.create();
        Connection conn = ConnectionFactory.createConnection(conf);


        return conn;

    }

    public boolean hasNamespace(Connection conn) throws IOException {

        Admin admin = conn.getAdmin();


        try {

        NamespaceDescriptor namespaceDescriptor = admin.getNamespaceDescriptor("alex");
        return  true;
        }catch (NamespaceNotFoundException e){
            return  false;
        }


    }

    public void createNamespcae(Connection conn) throws IOException {

        Admin admin = conn.getAdmin();

        NamespaceDescriptor namespaceDescriptor =  NamespaceDescriptor.create("alex").build();


        admin.createNamespace(namespaceDescriptor);


    }
}
