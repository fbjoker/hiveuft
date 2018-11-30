package com.alex.hbase.demo2.tool;

import com.alex.hbase.demo2.mapper.HbaseTbaleMapper;
import com.alex.hbase.demo2.reducer.HbaseTableReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobStatus;
import org.apache.hadoop.util.Tool;

public class Tbale2Table implements Tool {
    public int run(String[] args) throws Exception {

        Job job = Job.getInstance();


        job.setJarByClass(Tbale2Table.class);




//        job.setMapperClass();
//        job.setMapOutputKeyClass();
//        job.setMapOutputValueClass();
        TableMapReduceUtil.initTableMapperJob("fruit",new Scan(),
                HbaseTbaleMapper.class,
                ImmutableBytesWritable.class,
                Put.class,
                job);




//        job.setReducerClass();
//        job.setOutputKeyClass();
//        job.setOutputValueClass();

        TableMapReduceUtil.initTableReducerJob("fruit_mr", HbaseTableReducer.class,job);




        return  job.waitForCompletion(true) ? JobStatus.State.SUCCEEDED.getValue():
                JobStatus.State.FAILED.getValue();



    }

    public void setConf(Configuration conf) {

    }

    public Configuration getConf() {
        return null;
    }
}
