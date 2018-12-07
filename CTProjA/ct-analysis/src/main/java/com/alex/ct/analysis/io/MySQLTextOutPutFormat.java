package com.alex.ct.analysis.io;

import com.alex.ct.common.util.JDBCUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.output.FileOutputCommitter;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLTextOutPutFormat extends OutputFormat<Text,Text> {
    protected  static  class MySQLRecordWriter extends  RecordWriter<Text,Text>{



         Connection conn=JDBCUtil.getConn();

        public void write(Text key, Text value) throws IOException, InterruptedException {
            String val = value.toString();
            String[] data = val.split("_");
            int sumcall=Integer.parseInt(data[0]);
            int sumduration=Integer.parseInt(data[1]);

            PreparedStatement preparedStatement=null;


            try {

               String sql="insert into ct_call (telid,dateid,sumcall,sumduration)values(?,?,?,?)";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1,2);
                preparedStatement.setInt(2,3);
                preparedStatement.setInt(3,sumcall);
                preparedStatement.setInt(4,sumduration);


                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        }

        public void close(TaskAttemptContext context) throws IOException, InterruptedException {
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public RecordWriter<Text, Text> getRecordWriter(TaskAttemptContext context) throws IOException, InterruptedException {


        return new MySQLRecordWriter();
    }

    public void checkOutputSpecs(JobContext context) throws IOException, InterruptedException {

    }

    private FileOutputCommitter committer = null;
    public static Path getOutputPath(JobContext job) {
        String name = job.getConfiguration().get(FileOutputFormat.OUTDIR);
        return name == null ? null: new Path(name);
    }

    public OutputCommitter getOutputCommitter(TaskAttemptContext context) throws IOException, InterruptedException {
        if (committer == null) {
            Path output = getOutputPath(context);
            committer = new FileOutputCommitter(output, context);
        }
        return committer;
    }
}
