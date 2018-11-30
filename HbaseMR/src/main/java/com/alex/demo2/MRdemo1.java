package com.alex.demo2;


import com.alex.demo2.tool.Tbale2Table;
import org.apache.hadoop.util.ToolRunner;

public class MRdemo1 {
    public static void main(String[] args) throws Exception {

        ToolRunner.run(new Tbale2Table(),args);


    }
}
