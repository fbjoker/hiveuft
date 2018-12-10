package com.alex.ct.comsumer.dao;

import com.alex.ct.bean.BaseDao;
import com.alex.ct.comsumer.bean.Calllog;
import com.alex.ct.constant.Names;
import com.alex.ct.constant.ValConstant;

import java.io.IOException;

public class HbaseDao extends BaseDao {

     public void  init() throws IOException {

         creatNamespaceNX();
         creatTableXX(Names.TABLE.getvalue(),
                 ValConstant.PARTITIONNUM,
                " com.alex.ct.coprocessor.InsertCalleeCoprocessor",
                 Names.CF_CALLER.getvalue(),
                 Names.CF_CALLEE.getvalue());

     }



    public void insert(Calllog log) throws Exception {
         String rowkey= getRowkey(log.getCall1(),log.getCall2())+log.getCall1()+"_"+log.getCalltime()
                        +"_"+log.getCall2()+"_"+log.getDuration();

         log.setRowkey(rowkey);

         putData(log);


    }



}
