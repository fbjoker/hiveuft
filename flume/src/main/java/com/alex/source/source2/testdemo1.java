package com.alex.source.source2;

public class testdemo1 {
    public static void main(String[] args) {


        String sql="select * from stu where id >35";
        int length = sql.toString().length();

        String exesql= sql.toString().substring(0, length - String.valueOf(35).length()) ;

        System.out.println(exesql);
    }
}
