package com.alex.ct.util;

import java.text.DecimalFormat;

public class NumUtil {

    public  static String  format(int num , int len){

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            sb.append("0");
        }

        DecimalFormat decimalFormat = new DecimalFormat(sb.toString());
        String format = decimalFormat.format(num);


        return  format;
    }
}
