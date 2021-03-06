package com.alex.ct.comsumer.bean.dao;

import com.alex.ct.common.bean.BaseDao;
import com.alex.ct.common.constant.Names;
import com.alex.ct.common.constant.ValConstant;
import com.alex.ct.comsumer.bean.Calllog;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class HbaseDao extends BaseDao {


    /**
     * 初始化命名空间和表格
     * @throws IOException
     */
    public  void init() throws Exception {

        start();
        creatNamespaceNX(Names.NAMESPACE.getvalue());
        creatTableXX(Names.TABLE.getvalue(), ValConstant.REGION_COUNT,
                "com.alex.ct.consumer.coprocessor.InsertCalleeCoprocessor",
                Names.CF_CALLER.getvalue(),Names.CF_CALLEE.getvalue());

        end();
    }


    public  void insert(Calllog log) throws Exception {

        String rowKey=getRengionNum(log.getCall1(),log.getCalltime())+"_"+log.getCall1()+"_"+
                log.getCall2()+"_"+log.getCalltime()+"_"+log.getDuration();

        log.setRowkey(rowKey);

        putData(log);
    }




    public  void insert(String value) throws IOException {

        String[] data = value.split("\t");

        String call1=data[0];
        String call2=data[1];
        String calltime=data[2];
        String duration=data[3];


       String rowKey=getRengionNum(call1,calltime)+"_"+call1+"_"+calltime+
               "_"+call2+"_"+duration+"_1";

        Put put = new Put(Bytes.toBytes(rowKey));

        byte[] family = Bytes.toBytes(Names.CF_CALLER.getvalue());

        put.addColumn(family,Bytes.toBytes("call1"),Bytes.toBytes(call1));
        put.addColumn(family,Bytes.toBytes("call2"),Bytes.toBytes(call2));
        put.addColumn(family,Bytes.toBytes("calltime"),Bytes.toBytes(calltime));
        put.addColumn(family,Bytes.toBytes("duration"),Bytes.toBytes(duration));
        put.addColumn(family,Bytes.toBytes("flg"),Bytes.toBytes("1"));

//        String rowKey2=getRengionNum(call2,calltime)+"_"+call2+"_"+calltime+
//                "_"+call1+"_"+duration+"_0";
//        Put put2 = new Put(Bytes.toBytes(rowKey2));
//        byte[] family2 = Bytes.toBytes(Names.CF_CALLEE.getvalue());
//
//        put2.addColumn(family2,Bytes.toBytes("call1"),Bytes.toBytes(call2));
//        put2.addColumn(family2,Bytes.toBytes("call2"),Bytes.toBytes(call1));
//        put2.addColumn(family2,Bytes.toBytes("calltime"),Bytes.toBytes(calltime));
//        put2.addColumn(family2,Bytes.toBytes("duration"),Bytes.toBytes(duration));
//        put2.addColumn(family2,Bytes.toBytes("flg"),Bytes.toBytes("0"));
//
//
//        ArrayList<Put> puts = new ArrayList<Put>();
//        puts.add(put);
//        puts.add(put2);

        putData(Names.TABLE.getvalue(),put);

    }





}
