package com.alex.ct.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {


    //date转换为字符串
    public  static  String format(Date date ,String format){


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);



        return  simpleDateFormat.format(date);

    }

    //字符串转换为date
    public  static Date parse(String date,String format){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        Date date1=null;

        try {
            date1= simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date1;


    }
}
