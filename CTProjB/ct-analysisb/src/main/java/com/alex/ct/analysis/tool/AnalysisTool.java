package com.alex.ct.analysis.tool;

import com.alex.ct.analysis.io.AnalysisTextOutputFormat;
import com.alex.ct.analysis.mapper.AnalysisTextMapper;
import com.alex.ct.analysis.reducer.AnalysisTextReducer;
import com.alex.ct.constant.Names;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;

public class AnalysisTool implements Tool {
    public int run(String[] args) throws Exception {

        Configuration conf = HBaseConfiguration.create();
        Job job = Job.getInstance(conf);
        job.setJarByClass(AnalysisTool.class);

        Scan scan = new Scan();

        scan.addFamily(Bytes.toBytes(Names.CF_CALLER.getvalue()));

        TableMapReduceUtil.initTableMapperJob(Names.TABLE.getvalue(),
                scan,
                AnalysisTextMapper.class,
                Text.class,
                Text.class,
                job);



        job.setReducerClass(AnalysisTextReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setOutputFormatClass(AnalysisTextOutputFormat.class);





        boolean b = job.waitForCompletion(true);



        if(b){

            return 2;
        }else {

            return 3;
        }


    }

    public void setConf(Configuration conf) {

    }

    public Configuration getConf() {
        return null;
    }
}
