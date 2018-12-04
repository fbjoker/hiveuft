package com.alex.hbase.demo3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class HbaseAPI6 {

    public static void main(String[] args) throws IOException {

        Configuration conf = HBaseConfiguration.create();

        Connection conn = ConnectionFactory.createConnection(conf);

        TableName tableName = TableName.valueOf("alex:demo1");
        Table table = conn.getTable(tableName);

        Get get = new Get(Bytes.toBytes("150"));
        Result result = table.get(get);

        for (Cell cell : result.rawCells()) {

            byte[] rowkey = CellUtil.cloneRow(cell);
            byte[] family = CellUtil.cloneFamily(cell);
            byte[] qualifier= CellUtil.cloneQualifier(cell);
            byte[] val = CellUtil.cloneValue(cell);

            System.out.println(Bytes.toString(rowkey)+"\t"+
                    Bytes.toString(family)+"\t"+
                    Bytes.toString(qualifier)+"\t"+
                    Bytes.toString(val)
            );

        }


    }
}
