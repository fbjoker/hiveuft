package com.alex.ct.analysis.tool;

import com.alex.ct.analysis.io.MySQLTextOutPutFormat;
import com.alex.ct.analysis.mapper.AnalysisTextMapper;
import com.alex.ct.analysis.reducer.AnalysisTextReducer;
import com.alex.ct.common.constant.Names;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobStatus;
import org.apache.hadoop.util.Tool;


public class AnalysisTextTool  implements Tool {

    public int run(String[] args) throws Exception {
        Job job = Job.getInstance();
        job.setJarByClass(AnalysisTextTool.class);
        job.setNumReduceTasks(5);


        Scan scan = new Scan();
        scan.addFamily(Bytes.toBytes(Names.CF_CALLER.getvalue()));


        //mapper
        TableMapReduceUtil.initTableMapperJob(Names.TABLE.getvalue(),
                                                scan,
                                                AnalysisTextMapper.class,
                                                Text.class,
                                                Text.class,
                                                job
                                                );

        //reducer

        job.setReducerClass(AnalysisTextReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setOutputFormatClass(MySQLTextOutPutFormat.class);




        boolean b = job.waitForCompletion(true);

        if(b){
            return JobStatus.State.SUCCEEDED.getValue();
        }else {
            return JobStatus.State.FAILED.getValue();
        }


    }

    public void setConf(Configuration conf) {

    }

    public Configuration getConf() {
        return null;
    }
}
