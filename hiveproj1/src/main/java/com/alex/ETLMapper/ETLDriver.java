package com.alex.ETLMapper;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;

public class ETLDriver implements Tool {
    private  Configuration conf;
    public int run(String[] args) throws Exception {

        Job job = Job.getInstance(conf);
        job.setJarByClass(ETLDriver.class);
        job.setMapperClass(ETLMapper.class);

        job.setNumReduceTasks(0);

       // job.setMapOutputKeyClass(Text.class);
        //job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);


        //初始化输入路径

        initFileInputPath(job);






        return 0;
    }

    public void setConf(Configuration conf) {
    this.conf=conf;
    }

    public Configuration getConf() {
        return conf;
    }
}
