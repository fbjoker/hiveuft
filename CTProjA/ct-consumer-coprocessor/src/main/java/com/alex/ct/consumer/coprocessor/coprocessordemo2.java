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

public class coprocessordemo2 extends BaseRegionObserver {

    @Override
    public void postPut(ObserverContext<RegionCoprocessorEnvironment> e, Put put, WALEdit edit, Durability durability) throws IOException {

        Table table = e.getEnvironment().getTable(TableName.valueOf(Names.TABLE.getvalue()));


        byte[] row = put.getRow();
        String rowkey= Bytes.toString(row);

        //2_16569963779_20181224232656_17336673697_3175_1
        String[] data = rowkey.split("_");

        String call1=data[1];
        String call2=data[3];
        String calltime=data[2];
        String duration=data[4];
        String flg=data[5];
        if("1".equals(flg)){



        coprocessordao coprocessordao = new coprocessordao();
        int getkey = coprocessordao.getkey(call2, calltime);
        String rowkey2=getkey+"_"+call2+"_"+calltime+"_"+call1+"_"+duration+"_"+"0";
        Put put2 = new Put(Bytes.toBytes(rowkey2));
        put2.addColumn(Bytes.toBytes(Names.CF_CALLEE.getvalue()),Bytes.toBytes("call1"),Bytes.toBytes(call1));
        put2.addColumn(Bytes.toBytes(Names.CF_CALLEE.getvalue()),Bytes.toBytes("call2"),Bytes.toBytes(call2));
        put2.addColumn(Bytes.toBytes(Names.CF_CALLEE.getvalue()),Bytes.toBytes("calltime"),Bytes.toBytes(calltime));
        put2.addColumn(Bytes.toBytes(Names.CF_CALLEE.getvalue()),Bytes.toBytes("duration"),Bytes.toBytes(duration));
        put2.addColumn(Bytes.toBytes(Names.CF_CALLEE.getvalue()),Bytes.toBytes("flg"),Bytes.toBytes("0"));


        table.put(put2);
        table.close();
        }

    }


    private class  coprocessordao extends BaseDao{
        public int getkey(String tell,String time){
            return  getRengionNum(tell,time);

        }


    }

}
