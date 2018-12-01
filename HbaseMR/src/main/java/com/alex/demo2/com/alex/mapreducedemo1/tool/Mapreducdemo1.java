package com.alex.demo2.com.alex.mapreducedemo1.tool;

import org.apache.hadoop.util.ToolRunner;

public class Mapreducdemo1 {
    public static void main(String[] args) throws Exception {


        ToolRunner.run(new Table2table(),args);

    }
}
