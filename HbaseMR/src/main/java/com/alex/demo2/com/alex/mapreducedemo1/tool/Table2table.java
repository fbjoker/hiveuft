package com.alex.demo2.com.alex.mapreducedemo1.tool;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;

public class Table2table implements Tool {
    public int run(String[] args) throws Exception {


        Job job = Job.getInstance();
        job.setJarByClass(Table2table.class);



        return 0;
    }

    public void setConf(Configuration conf) {

    }

    public Configuration getConf() {
        return null;
    }
}
