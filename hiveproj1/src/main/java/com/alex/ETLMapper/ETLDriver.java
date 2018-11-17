package com.alex.ETLMapper;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import javax.swing.plaf.TreeUI;
import java.io.IOException;

public class ETLDriver implements Tool {
    private  Configuration conf;
    public int run(String[] args) throws Exception {

        conf.set("inputPath",args[0]);
        conf.set("outputPath",args[1]);


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

        //初始化输入路径
        initFileOutputPath(job);

        boolean b = job.waitForCompletion(true);




        return b?0:1;
    }

    private void initFileOutputPath(Job job) throws IOException {
        String output = conf.get("outputPath");
        Path outPath = new Path(output);

        FileSystem fileSystem = FileSystem.get(conf);
               if( fileSystem.exists(outPath)){
                   fileSystem.delete(outPath, true);
               }else {
                   FileOutputFormat.setOutputPath(job,outPath);
               }

    }

    private void initFileInputPath(Job job) throws IOException {
        //获取路径
        String input = conf.get("inputPath");


        Path inputPath = new Path(input);

        //获取文件系统
        FileSystem fileSystem = FileSystem.get(conf);
        if ( fileSystem.exists(inputPath)){
            FileInputFormat.setInputPaths(job,inputPath);
        }else {
            throw  new RuntimeException("文件夹不存在");
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
