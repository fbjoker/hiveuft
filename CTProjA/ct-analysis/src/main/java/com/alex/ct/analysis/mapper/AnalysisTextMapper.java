package com.alex.ct.analysis.mapper;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class AnalysisTextMapper extends TableMapper<Text,Text> {


    @Override
    protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {

        String rowkey = Bytes.toString(key.get());
        String[] val = rowkey.split("_");

        String call1=val[1];
        String call2=val[3];
        String calltime=val[2];
        String duration=val[4];
        String year = calltime.substring(0, 4);
        String month = calltime.substring(0, 6);
        String day = calltime.substring(0, 8);

        context.write(new Text(call1+"_"+year),new Text(duration));
        context.write(new Text(call1+"_"+month),new Text(duration));
        context.write(new Text(call1+"_"+day),new Text(duration));

        context.write(new Text(call2+"_"+year),new Text(duration));
        context.write(new Text(call2+"_"+month),new Text(duration));
        context.write(new Text(call2+"_"+day),new Text(duration));


   }
}
