package com.alex.ct.bean;

import com.alex.ct.api.Colume;
import com.alex.ct.api.RowKey;
import com.alex.ct.api.TableRef;
import com.alex.ct.constant.Names;
import com.alex.ct.constant.ValConstant;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BaseDao {


    ThreadLocal<Admin> adminHandler=new ThreadLocal<Admin>();
    ThreadLocal<Connection> connHandler=new ThreadLocal<Connection>();


    protected  void  start() throws IOException {
        getAdmin();
                getconn();




    }

    protected  void  end() throws IOException {
        Admin admin = adminHandler.get();
        if (admin!=null){
            admin.close();
            adminHandler.remove();
        }

        Connection conn = connHandler.get();
        if(conn!=null){
            conn.close();
            connHandler.remove();
        }


    }

    protected  Connection getconn(){
        Connection conn = connHandler.get();
        if(conn==null){

        }


        return  conn;
    }

    protected  Admin getAdmin() throws IOException {
        Admin admin = adminHandler.get();
        if(admin==null){
            Connection conn = connHandler.get();
            admin=conn.getAdmin();

        }

        return  admin;

    }


    protected void creatTableXX(String name,int paritoncount,String observer,String...colum) throws IOException {
        Admin admin = adminHandler.get();
        TableName tableName = TableName.valueOf(name);
        boolean b = admin.tableExists(tableName);
        if(b){
            admin.disableTable(tableName);
            admin.deleteTable(tableName);

        }

        HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
        for (String s : colum) {


        HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(s);
        hTableDescriptor.addFamily(hColumnDescriptor);
        }
        hTableDescriptor.addCoprocessor(observer);
        byte[][] splitkey = getSplitkey(paritoncount);
        admin.createTable(hTableDescriptor,splitkey);



    }

    protected void creatNamespaceNX() throws IOException {
        Admin admin = adminHandler.get();

        try {
            admin.getNamespaceDescriptor(Names.NAMESPACE.getvalue());
        } catch (IOException e) {
            e.printStackTrace();
            NamespaceDescriptor ns = NamespaceDescriptor.create(Names.NAMESPACE.getvalue()).build();
            admin.createNamespace(ns);


        }

    }


    protected int getRowkey(String call1, String call2) {

        int i = call1.hashCode();
        int j = call2.hashCode();
        int key = i ^ j;


        return  key% ValConstant.PARTITIONNUM;


    }

    protected  byte[][] getSplitkey(int partiton){
        int splitkey=partiton-1;

         byte[][] sp= new byte[splitkey][];

        List<byte[]> list= new ArrayList<byte[]>();

        for (int i = 0; i < splitkey; i++) {
            list.add(Bytes.toBytes(i+"|"));

        }

        list.toArray(sp);


        return sp;
    }


    protected void putData(Object obj) throws IOException, IllegalAccessException {

        Class clazz = obj.getClass();

        TableRef tableanotation = (TableRef) clazz.getAnnotation(TableRef.class);

        if (tableanotation!=null){

            TableName tableName = TableName.valueOf(tableanotation.value());


            Table table = getconn().getTable(tableName);


            Field[] fields = clazz.getDeclaredFields();
            String rowkey="";

            for (Field field : fields) {

                RowKey rk = field.getAnnotation(RowKey.class);

                if (rowkey!=null){
                    field.setAccessible(true);
                    rowkey= (String) field.get(obj);
                    break;
                }


            }

            Put put = new Put(Bytes.toBytes(rowkey));

            for (Field field : fields) {

                Colume colume = field.getAnnotation(Colume.class);
                if (colume!=null) {

                    String family = colume.family();
                    String colum = colume.colum();
                    if(colum==null||"".equals(colum)) {
                        colum=field.getName();

                    }
                    String val = (String) field.get(obj);
                    field.setAccessible(true);


                    put.addColumn(Bytes.toBytes(family), Bytes.toBytes(colum), Bytes.toBytes(val));
                }

            }

            table.put(put);

            }





        }



    }









