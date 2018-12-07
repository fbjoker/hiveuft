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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MySQLTextOutPutFormat extends OutputFormat<Text,Text> {
    protected  static  class MySQLRecordWriter extends  RecordWriter<Text,Text>{



         private Connection conn=null;
         Map<String,Integer> date= new HashMap<String, Integer>();
         Map<String,Integer> user= new HashMap<String, Integer>();





         public MySQLRecordWriter(){


             conn=JDBCUtil.getConn();

             PreparedStatement ps=null;
             ResultSet resultSet=null;
             try {
             String  sqluser="select tel,id from ct_user  ";
                 ps= conn.prepareStatement(sqluser);



                 resultSet = ps.executeQuery();
                 while (resultSet.next()){
                     String tel=resultSet.getString(1);
                     Integer id = resultSet.getInt(2);

                     user.put(tel,id);

                 }



                 sqluser="select id,year,month,day from ct_date  ";
                 ps= conn.prepareStatement(sqluser);



                 resultSet = ps.executeQuery();
                 while (resultSet.next()){

                     Integer id = resultSet.getInt(1);
                     String year=resultSet.getString(2);
                     String month=resultSet.getString(3);
                     if(month.length()==1){
                         month="0"+month;
                     }
                     String day=resultSet.getString(4);
                     if(day.length()==1){
                         day="0"+month;
                     }

                     date.put(year+month+day,id);

                 }


             } catch (SQLException e) {
                 e.printStackTrace();
             }finally {
                 if(ps!=null){

                     try {
                         ps.close();
                     } catch (SQLException e) {
                         e.printStackTrace();
                     }
                 }
                 if(resultSet!=null){
                     try {
                         resultSet.close();
                     } catch (SQLException e) {
                         e.printStackTrace();
                     }
                 }
             }


         }

        public void write(Text key, Text value) throws IOException, InterruptedException {
            String val = value.toString();
            String[] data = val.split("_");
            int sumcall=Integer.parseInt(data[0]);
            int sumduration=Integer.parseInt(data[1]);

            String tell = key.toString();
            String[] telldata = val.split("_");
            int tel=Integer.parseInt(data[0]);
            int calltime=Integer.parseInt(data[1]);

            PreparedStatement preparedStatement=null;


            try {

               String sql="insert into ct_call (telid,dateid,sumcall,sumduration)values(?,?,?,?)";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1,user.get(tel));
                preparedStatement.setInt(2,date.get(calltime));
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
