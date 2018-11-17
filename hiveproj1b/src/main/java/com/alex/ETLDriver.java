package com.alex;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;
import java.io.OutputStream;

public class ETLDriver implements Tool {
    private Configuration conf;
    public int run(String[] args) throws Exception {




        Job job = Job.getInstance(conf);
        job.setJarByClass(ETLDriver.class);
        job.setMapperClass(ETLMapper.class);
        job.setNumReduceTasks(0);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        conf.set("in",args[0]);
        conf.set("out",args[1]);
        inputFilePath(job);
        outputFilePath(job);


        return 0;
    }

    private void outputFilePath(Job job) throws IOException {
        String out = conf.get("out");
        Path outpath = new Path(out);
        FileSystem fileSystem = FileSystem.get(conf);
        if(fileSystem.exists(outpath)){
            fileSystem.delete(outpath,true);

        }else {
            FileOutputFormat.setOutputPath(job,outpath);
        }
    }

    private void inputFilePath(Job job) throws IOException {
        String in = conf.get("in");
        Path inpath = new Path(in);

        FileSystem fileSystem = FileSystem.get(conf);
        if(fileSystem.exists(inpath)){
            FileInputFormat.setInputPaths(job,inpath);
        }else {
            throw  new RuntimeException();
        }


    }

    public void setConf(Configuration conf) {
    this.conf=conf;
    }

    public Configuration getConf() {
        return conf;
    }


    public static void main(String[] args) throws Exception {
        int run = ToolRunner.run(new ETLDriver(), args);

        System.exit(run);
    }
}
