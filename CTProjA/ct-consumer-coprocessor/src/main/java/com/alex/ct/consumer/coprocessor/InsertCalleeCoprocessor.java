package com.alex.ct.consumer.coprocessor;


import com.alex.ct.common.bean.BaseDao;
import com.alex.ct.common.constant.Names;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Durability;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.coprocessor.BaseRegionObserver;
import org.apache.hadoop.hbase.coprocessor.ObserverContext;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.regionserver.wal.WALEdit;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * 协处理器
 */
public class InsertCalleeCoprocessor extends BaseRegionObserver {


    @Override
    public void postPut(ObserverContext<RegionCoprocessorEnvironment> e, Put put, WALEdit edit, Durability durability) throws IOException {

        Table table = e.getEnvironment().getTable(TableName.valueOf(Names.TABLE.getvalue()));
        String rowkey = Bytes.toString(put.getRow());
        String[] data = rowkey.split("_");
        //为了使用basedao里面的getregion生成key
        CoprocessorDao coprocessorDao = new CoprocessorDao();



        String call1=data[1];
        String call2=data[3];
        String calltime=data[2];
        String duration=data[4];
        String flg=data[5];

        if("1".equals(flg)){
            int key = coprocessorDao.getRegin(call2, calltime);
            String  rowKey2=key+"_"+call2+"_"+calltime+
                    "_"+call1+"_"+duration+"_0";
            //System.out.println("callee:"+rowKey2);

            Put put2 = new Put(Bytes.toBytes(rowKey2));
            byte[] family2 = Bytes.toBytes(Names.CF_CALLEE.getvalue());

            put2.addColumn(family2,Bytes.toBytes("call1"),Bytes.toBytes(call2));
            put2.addColumn(family2,Bytes.toBytes("call2"),Bytes.toBytes(call1));
            put2.addColumn(family2,Bytes.toBytes("calltime"),Bytes.toBytes(calltime));
            put2.addColumn(family2,Bytes.toBytes("duration"),Bytes.toBytes(duration));
            put2.addColumn(family2,Bytes.toBytes("flg"),Bytes.toBytes("0"));

            table.put(put2);
            table.close();
        }




    }

    private  class  CoprocessorDao extends BaseDao{

        public  int getRegin(String tel, String time){
             return getRengionNum(tel,time);
        }

    }
}
