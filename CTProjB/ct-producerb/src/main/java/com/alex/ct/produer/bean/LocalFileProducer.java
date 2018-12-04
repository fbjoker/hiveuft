package com.alex.ct.produer.bean;

import com.alex.ct.bean.DataIn;
import com.alex.ct.bean.DataOut;
import com.alex.ct.bean.Producer;
import com.alex.ct.util.DateFormat;
import com.alex.ct.util.NumUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class LocalFileProducer implements Producer {

    private  DataOut out;
    private  DataIn in;
    private  boolean flg;


    public LocalFileProducer() {
    }

    public void setIn(DataIn in) {
        this.in=in;

    }

    public void setOut(DataOut out) {
        this.out=out;

    }

    public void produce() {


        //读取数据
        try {
            List<Contact> contacts = in.read(Contact.class);


        //业务逻辑
            //2个电话
            int tell1index= new Random().nextInt(contacts.size());
            int tell2index= new Random().nextInt(contacts.size());
            while (true){

                if(tell1index!=tell2index){
                    break;
                }

            }

            Contact call1= contacts.get(tell1index);
            Contact call2= contacts.get(tell2index);



            //电话时间
            //1514736000000
            //1546272000000

            String start= "20180101000000";
            String end= "20190101000000";

            long st = DateFormat.str2date(start, "yyyyMMddHHmmss").getTime();
            long ed = DateFormat.str2date(end, "yyyyMMddHHmmss").getTime();

            long ct= st +(long)((ed-st)*Math.random()+1);

            String cTime = DateFormat.date2str(new Date(ct), "yyyyMMddHHmmss");
            //电话时长
            int time=new Random().nextInt(3600);

            String calltime = NumUtil.format(time, 4);


            //生成一个log
            Calllog calllog = new Calllog(call1.getTel(), call2.getTel(), cTime, calltime);


            System.out.println(calllog.toString());
            //写到文件
          //  out.write(calllog);




        //写出数据
            out.write(calllog);
            Thread.sleep(500);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void close() throws IOException {

        if( in!=null){
            in.close();
        }

        if (out!=null){
            out.close();
        }

    }
}
