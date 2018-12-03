package com.alex.hbase.dao;

import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InBoxDao {
    public void createTbale(Connection conn) throws IOException {

        Admin admin = conn.getAdmin();
        TableName tableName = TableName.valueOf("alex:inbox");
        boolean b = admin.tableExists(tableName);

        if (b) {
            admin.disableTable(tableName);

            admin.deleteTable(tableName);
        }

        HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
        HColumnDescriptor hColumnDescriptor = new HColumnDescriptor("info");
        hTableDescriptor.addFamily(hColumnDescriptor);
        hColumnDescriptor.setMaxVersions(5);
        hColumnDescriptor.setMinVersions(5);

        admin.createTable(hTableDescriptor);


        admin.close();
    }

    public void insertDatas(Connection conn, List<String> fansid, String userid,String rowkey) throws IOException {

        TableName tableName = TableName.valueOf("alex:inbox");

        Table table = conn.getTable(tableName);
        List<Put> puts = new ArrayList<Put>();
        for (String s : fansid) {
        Put put = new Put(Bytes.toBytes(s));
            byte[] f = Bytes.toBytes("info");
            byte[] q = Bytes.toBytes(userid);
            byte[] c = Bytes.toBytes(rowkey);



            put.addColumn(f,q,c);



        }

        table.put(puts);

        table.close();


    }

    public void insertAttendDatas(Connection conn, String fanUser, String starUser, List<String> weiborowkey) throws IOException {
        TableName tableName = TableName.valueOf("alex:inbox");
        Table table = conn.getTable(tableName);


        List<Put> puts=new ArrayList<Put>();

        int count=0;
        for (String s : weiborowkey) {
            Put put = new Put(Bytes.toBytes(fanUser));
            byte[] family = Bytes.toBytes("info");
            byte[] column = Bytes.toBytes(starUser);
            byte[] value = Bytes.toBytes(s);
            //put.addColumn(family,column,value);
            long ts = System.currentTimeMillis()+count++;
            put.addColumn(family,column,ts,value);

            puts.add(put);
        }

    }

    public List<String> getWeiBokey(Connection conn, String fanUser, String starUser) throws IOException {

        TableName tableName = TableName.valueOf("alex:inbox");

        Table table = conn.getTable(tableName);

        List<String> rowkeys= new ArrayList<String>();

        Get get = new Get(Bytes.toBytes(fanUser));
        byte[] family = Bytes.toBytes("attend");
        byte[] colum = Bytes.toBytes(starUser);
        get.addColumn(family,colum);
                get.setMaxVersions(5);

        Result result = table.get(get);

        for (Cell cell : result.rawCells()) {
            rowkeys.add(Bytes.toString(CellUtil.cloneValue(cell)));

        }


        table.close();

        return rowkeys;
    }

    public void delweobo(Connection conn, String fanUser, String starUser) throws IOException {

        TableName tableName = TableName.valueOf("alex:inbox");

        Table table = conn.getTable(tableName);

        Delete delete = new Delete(Bytes.toBytes(fanUser));
        byte[] family = Bytes.toBytes("info");
        byte[] colume = Bytes.toBytes(starUser);
        delete.addColumns(family,colume);
        table.delete(delete);

        table.close();

    }
}
