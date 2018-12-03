package com.alex.ct.common.util;

import java.text.DecimalFormat;

public class NumberUtil {
    public  static  String format(int num ,int len){

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stringBuilder.append("0");
        }

        //0000
        DecimalFormat df= new DecimalFormat(stringBuilder.toString());
        return df.format(num);

    }
}
