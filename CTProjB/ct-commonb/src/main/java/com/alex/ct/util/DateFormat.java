package com.alex.ct.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

    public  static Date str2date(String str,String format){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        try {
           return simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  null;


    }



    public  static  String  date2str(Date date,String format){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);


        return  simpleDateFormat.format(date);




    }


}
