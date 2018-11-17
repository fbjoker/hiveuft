package com.alex.ETLMapper;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class ETLMapper extends Mapper<LongWritable, Text,Text, NullWritable> {

    Text k=new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1获取数据

        String s = value.toString();
        //清洗数据
        String s1 = ETLUtils.etlString(s);

        //写出数据
        if(StringUtils.isBlank(s1)) return;

        k.set(s1);
        context.write(k,NullWritable.get());

    }
}
