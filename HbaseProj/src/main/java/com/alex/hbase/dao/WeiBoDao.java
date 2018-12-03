package com.alex.hbase.dao;

import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeiBoDao {
    public void createTbale(Connection conn) throws IOException {


        Admin admin = conn.getAdmin();
        TableName tableName = TableName.valueOf("alex:weibo");
        boolean b = admin.tableExists(tableName);

        if (b) {
            admin.disableTable(tableName);

            admin.deleteTable(tableName);
        }

        HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
        HColumnDescriptor hColumnDescriptor = new HColumnDescriptor("info");
        hTableDescriptor.addFamily(hColumnDescriptor);
        admin.createTable(hTableDescriptor);


        admin.close();

    }

    public String insertData(Connection conn, String userid, long time, String content) throws IOException {
        TableName tableName = TableName.valueOf("alex:weibo");
        Table table = conn.getTable(tableName);
        String rowkey=userid+"_"+(Long.MAX_VALUE-time);

        Put put = new Put(Bytes.toBytes(rowkey));
        byte[] family = Bytes.toBytes("info");
        byte[] q = Bytes.toBytes("content");
        byte[] c = Bytes.toBytes(content);

        put.addColumn(family,q,c);

        table.put(put);


        table.close();

        return rowkey;

    }

    public List<String> scanData(Connection conn, String starUser) throws IOException {
        TableName tableName = TableName.valueOf("alex:weibo");

        Table table = conn.getTable(tableName);
        Scan scan = new Scan();

        scan.setStartRow(Bytes.toBytes(starUser+"-"));
        scan.setStopRow(Bytes.toBytes(starUser+"-|"));

        ResultScanner results = table.getScanner(scan);
        List<String> rowkeys=new ArrayList<String>();
        for (Result result : results) {

            rowkeys.add(Bytes.toString(result.getRow()));
//            for (Cell cell : result.rawCells()) {
//                rowkeys.add(Bytes.toString(CellUtil.cloneRow(cell)));
//
//            }

            if (rowkeys.size()>=5){
                break;
            }
        }




    results.close();
        return rowkeys;
    }
}
