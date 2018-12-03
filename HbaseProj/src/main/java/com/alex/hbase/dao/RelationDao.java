package com.alex.hbase.dao;

import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RelationDao {
    public void createTbale(Connection conn) throws IOException {

        Admin admin = conn.getAdmin();
        TableName tableName = TableName.valueOf("alex:relation");
        boolean b = admin.tableExists(tableName);

        if (b) {
            admin.disableTable(tableName);

            admin.deleteTable(tableName);
        }

        HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
        HColumnDescriptor hColumnDescriptor = new HColumnDescriptor("fans");
        hTableDescriptor.addFamily(hColumnDescriptor);
        HColumnDescriptor hColumnDescriptor2 = new HColumnDescriptor("relation");
        hTableDescriptor.addFamily(hColumnDescriptor2);
        admin.createTable(hTableDescriptor);


        admin.close();
    }

    public List<String> getFansIds(Connection conn, String userid) throws IOException {

        List<String> fansids= new ArrayList<String>();

        TableName tableName = TableName.valueOf("alex:relation");
        Table table = conn.getTable(tableName);

        Get get = new Get(Bytes.toBytes(userid));
        get.addFamily(Bytes.toBytes("fans"));


        Result result = table.get(get);
        for (Cell cell : result.rawCells()) {
            fansids.add(Bytes.toString(CellUtil.cloneValue(cell)));
        }

        return fansids;

    }

    public void insertAttendData(Connection conn, String fanUser, String starUser) throws IOException {

        TableName tableName = TableName.valueOf("alex:relation");

        Table table = conn.getTable(tableName);

        Put put = new Put(Bytes.toBytes(fanUser));
        byte[] family = Bytes.toBytes("attend");
        byte[] q = Bytes.toBytes(starUser);
        byte[] val = Bytes.toBytes(starUser);

        put.addColumn(family,q,val);
        table.put(put);

        table.close();


    }

    public void insertFansData(Connection conn, String starUser, String fanUser) throws IOException {
        TableName tableName = TableName.valueOf("alex:relation");

        Table table = conn.getTable(tableName);

        Put put = new Put(Bytes.toBytes(fanUser));
        byte[] family = Bytes.toBytes("fans");
        byte[] q = Bytes.toBytes(fanUser);
        byte[] val = Bytes.toBytes(fanUser);

        put.addColumn(family,q,val);
        table.put(put);

        table.close();

    }
}
