package com.alex.ct.analysis.io;

import com.alex.ct.util.JDBCUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.output.FileOutputCommitter;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AnalysisTextOutputFormat extends OutputFormat<Text,Text> {

    protected static class MySQLRecordWriter<K, V> extends RecordWriter<K, V> {
        Connection conn;

        public MySQLRecordWriter(){
            conn= JDBCUtil.getconn();
        }

        public void write(K key, V value) throws IOException, InterruptedException {
            String[] data = key.toString().split("_");
            String telid=data[0];
            String dateid=data[1];


            String[] val = value.toString().split("_");
            String sumcall=val[0];
            String sumcalltime=val[1];
            String sql= "insert into ct_call ( )values(?,?,?,?)";

            PreparedStatement preparedStatement;
            try {
                preparedStatement=conn.prepareStatement(sql);
                preparedStatement.setInt(1,2);
                preparedStatement.setInt(2,2);
                preparedStatement.setInt(3,Integer.parseInt(sumcall));
                preparedStatement.setInt(4,Integer.parseInt(sumcalltime));

                preparedStatement.executeUpdate();




            } catch (SQLException e) {
                e.printStackTrace();
            }


        }

        public void close(TaskAttemptContext context) throws IOException, InterruptedException {

        }
    }

    public RecordWriter<Text, Text> getRecordWriter(TaskAttemptContext context) throws IOException, InterruptedException {
        return new MySQLRecordWriter();
    }

    public void checkOutputSpecs(JobContext context) throws IOException, InterruptedException {

    }

    public static Path getOutputPath(JobContext job) {
        String name = job.getConfiguration().get(FileOutputFormat.OUTDIR);
        return name == null ? null: new Path(name);
    }

    private FileOutputCommitter committer = null;
    public OutputCommitter getOutputCommitter(TaskAttemptContext context) throws IOException, InterruptedException {
        if (committer == null) {
            Path output = getOutputPath(context);
            committer = new FileOutputCommitter(output, context);
        }
        return committer;
    }



}
