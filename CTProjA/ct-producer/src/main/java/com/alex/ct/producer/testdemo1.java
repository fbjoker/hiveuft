package com.alex.ct.producer;

import com.alex.ct.common.util.DateUtil;

import java.util.Date;

public class testdemo1 {

    public static void main(String[] args) {

        String startDate = "20180101000000";
        String endDate = "20190101000000";
        Date date= new Date(1514736000000l);
        Date date2= new Date(1546272000000l);

        long startTime = DateUtil.parse(startDate, "yyyyMMddHHmmss").getTime();
        long endTime = DateUtil.parse(endDate, "yyyyMMddHHmmss").getTime();
        System.out.println(startTime);
        System.out.println(endTime);
        System.out.println(DateUtil.parse(startDate, "yyyyMMddHHmmss"));

        // 通话时间
        long calltime = startTime + (long)((endTime - startTime) * Math.random());
        // 通话时间字符串
        String ss = DateUtil.format(date, "yyyyMMddHHmmss");
        System.out.println(ss);


    }
}
