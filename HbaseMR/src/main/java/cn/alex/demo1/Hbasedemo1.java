package cn.alex.demo1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;

public class Hbasedemo1 {

    public static void main(String[] args) throws IOException {

        Configuration conf = HBaseConfiguration.create();
        //conf.set("hbase.zookeeper.quorum", "hadoop102");
        // conf.set("hbase.zookeeper.property.clientPort", "2181");

        Connection conn = ConnectionFactory.createConnection(conf);


        Admin admin = conn.getAdmin();
        HBaseAdmin hBaseAdmin = new HBaseAdmin(conf);

        TableName tableName = TableName.valueOf("alex:student");
        boolean b = admin.tableExists(tableName);

//        if (b) {
//            //删除表格
//
//            DeletTable(admin, tableName);
//
//        }
//
//        //创建表
//        CreateTable(admin, tableName);


        Table table = conn.getTable(tableName);

        String rowkey="1001";

        ArrayList<Put> puts = new ArrayList<Put>();

        for (int i=0;i<20;i++){



        Put put = new Put(Bytes.toBytes(rowkey+i));
        byte[] family = Bytes.toBytes("info");
        byte[] qualifier = Bytes.toBytes("age"+(int) (Math.random()*100));
        byte[] val = Bytes.toBytes(""+(int) (Math.random()*100));
        put.addColumn(family,qualifier,val);
        puts.add(put);

        }
        System.out.println(puts.size());

        table.put(puts);
        System.out.println("数据添加成功");


        //扫描对象
        Scan scan = new Scan();
        scan.setStartRow(Bytes.toBytes(""+5));
        scan.setStopRow(Bytes.toBytes(10));

        ResultScanner res = table.getScanner(scan);

        for (Result re : res) {
            Cell[] cells = re.rawCells();
            for (Cell cell : cells) {

                System.out.println("rowkey:"+Bytes.toString(CellUtil.cloneRow(cell)));
                System.out.println("f:"+Bytes.toString(CellUtil.cloneFamily(cell)));
                System.out.println("q:"+Bytes.toString(CellUtil.cloneQualifier(cell)));
                System.out.println("v:"+Bytes.toString(CellUtil.cloneValue(cell)));

            }


        }



        //删除数据
        Delete delete = new Delete(Bytes.toBytes("1001"));
        //delete.addColumn(Bytes.toBytes("info"),Bytes.toBytes("age"));
        table.delete(delete);





        admin.close();
        conn.close();

//        System.out.println(b);


    }

    private static void DeletTable(Admin admin, TableName tableName) throws IOException {
        admin.disableTable(tableName);
        admin.deleteTable(tableName);

        System.out.println("删除表格成功");
    }

    private static void CreateTable(Admin admin, TableName tableName) throws IOException {
        HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);

        HColumnDescriptor hColumnDescriptor = new HColumnDescriptor("info");

        hTableDescriptor.addFamily(hColumnDescriptor);

        admin.createTable(hTableDescriptor);

        System.out.println("创建表格完成");
    }
}
