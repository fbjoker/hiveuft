package com.alex.ct.producer.bean;

import com.alex.ct.common.bean.DataIn;
import com.alex.ct.common.bean.DataOut;
import com.alex.ct.common.bean.Producer;
import com.alex.ct.common.util.DateUtil;
import com.alex.ct.common.util.NumberUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 本地数据的生产者
 */
public class LocalFileProducer implements Producer {

    private DataIn in;
    private DataOut out;
    private volatile boolean flg = true;

    public void setIn(DataIn in) {

        this.in = in;

    }

    public void setOut(DataOut out) {
        this.out = out;

    }


    public void Produce() {

        //读取通信录
        try {
            List<Contact> contacts = in.read(Contact.class);
//            for (Contact contact : contacts) {
//                System.out.println(contact);
//
//            }


            while (flg) {


                //随机2个电话
                int tel1index = new Random().nextInt(contacts.size());
                int tel2index;
                while (true) {
                    tel2index = new Random().nextInt(contacts.size());
                    if (tel1index != tel2index) {

                        break;
                    }
                }
                Contact call1 = contacts.get(tel1index);
                Contact call2 = contacts.get(tel2index);


                //随机通话时间
                String start = "20180101000000";
                String end = "20190101000000";

                long st= DateUtil.parse(start,"yyyyMMddHHmmss").getTime();
                long et= DateUtil.parse(end,"yyyyMMddHHmmss").getTime();

                long ct= st +(long)((et-st)*Math.random()+1);

                String cTime = DateUtil.format(new Date(ct), "yyyyMMddHHmmss");


                //随机时长
                int callTime = new Random().nextInt(3600);
                String callTimefm = NumberUtil.format(callTime, 4);


                //通话记录
                Calllog calllog = new Calllog(call1.getTel(),call2.getTel(),cTime,callTimefm);

               // System.out.println(calllog);
                //刷写文件
                out.write(calllog);


               // Thread.sleep(500);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void close() throws IOException {
        if (in != null) {
            in.close();

        }

        if (out != null) {
            out.close();
        }


    }
}
