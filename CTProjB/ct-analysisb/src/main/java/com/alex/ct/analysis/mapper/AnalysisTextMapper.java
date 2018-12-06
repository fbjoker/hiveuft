package com.alex.ct.analysis.mapper;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class AnalysisTextMapper extends TableMapper<Text,Text> {

    @Override
    protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {

        String rowkey = key.toString();
        String[] data = rowkey.split("_");

        String call1=data[1];
        String call2=data[3];
        String calltime=data[2];
        String duration=data[4];

        String year= calltime.substring(0,4);
        String month= calltime.substring(0,6);
        String day= calltime.substring(0,8);


        //数据分成 call-日期,   时长

        context.write(new Text(call1+"_"+year),new Text(duration));
        context.write(new Text(call1+"_"+month),new Text(duration));
        context.write(new Text(call1+"_"+day),new Text(duration));

        context.write(new Text(call2+"_"+year),new Text(duration));
        context.write(new Text(call2+"_"+month),new Text(duration));
        context.write(new Text(call2+"_"+day),new Text(duration));




    }
}
