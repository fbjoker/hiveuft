package com.alex.hiveutf;

import com.sun.tools.corba.se.idl.constExpr.EvaluationException;
import org.apache.hadoop.hive.ql.exec.UDF;

public class HiveUdf extends UDF {

 public  String evaluate(final String s){

     if(s==null){
         return  null;
     }

     return s.toLowerCase();
 }
}
