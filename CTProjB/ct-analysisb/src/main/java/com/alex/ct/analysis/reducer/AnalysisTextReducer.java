package com.alex.ct.analysis.reducer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class AnalysisTextReducer extends Reducer<Text,Text,Text,Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        int sumcall=0;
        int sumcalltime=0;
        for (Text value : values) {
            int time = Integer.parseInt(value.toString());
            sumcalltime+=time;
            sumcall++;


        }



        context.write(key,new Text(sumcall+"_"+sumcalltime));

    }
}
