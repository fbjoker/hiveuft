package com.alex.ct.coprocessor;

import com.alex.ct.bean.BaseDao;
import com.alex.ct.constant.Names;
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

public class InsertCalleeCoprocessor extends BaseRegionObserver {

    @Override
    public void postPut(ObserverContext<RegionCoprocessorEnvironment> e, Put put, WALEdit edit, Durability durability) throws IOException {
        Table table = (Table) e.getEnvironment().getTable(TableName.valueOf(Names.TABLE.getvalue()));

        String rowkey = Bytes.toString(put.getRow());


        String[] split = rowkey.split("_");
        Observedao dao = new Observedao();


        String call1=split[1];
        String call2=split[3];
        String calltime=split[2];
        String duration=split[4];
        String flag=split[5];

        if("1".equals(flag)){

            String rowkey2= dao.getRowkey(call2,calltime)+"_"+call1+"_"+calltime+"_"+call2+"_"+duration+"0";



            Put put1 = new Put(Bytes.toBytes(rowkey2));
            put1.addColumn(Bytes.toBytes(Names.CF_CALLEE.getvalue()),Bytes.toBytes("call1"),Bytes.toBytes(call1));
            put1.addColumn(Bytes.toBytes(Names.CF_CALLEE.getvalue()),Bytes.toBytes("call2"),Bytes.toBytes(call2));
            put1.addColumn(Bytes.toBytes(Names.CF_CALLEE.getvalue()),Bytes.toBytes("calltime"),Bytes.toBytes(calltime));
            put1.addColumn(Bytes.toBytes(Names.CF_CALLEE.getvalue()),Bytes.toBytes("duration"),Bytes.toBytes(duration));
            put1.addColumn(Bytes.toBytes(Names.CF_CALLEE.getvalue()),Bytes.toBytes("flg"),Bytes.toBytes("0"));


            table.put(put1);
            table.close();


        }






        }

    private  class  Observedao extends BaseDao{

        @Override
        protected int getRowkey(String call1, String call2) {
            return getRowkey(call1, call2);
        }
    }
}
