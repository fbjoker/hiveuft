package com.alex.ct.comsumer.bean;

import com.alex.ct.bean.Comsumer;
import com.alex.ct.comsumer.dao.HbaseDao;
import com.alex.ct.constant.Names;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class CalllogComsumer implements Comsumer {


    public void comsumer()  {
        Properties prop = new Properties();
        try {
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("consumer.properties"));
            KafkaConsumer<String,String> comsumer = new KafkaConsumer<String,String>(prop);

            comsumer.subscribe(Arrays.asList(Names.TOPIC.getvalue()));

            HbaseDao hbaseDao = new HbaseDao();

            while (true){

            ConsumerRecords<String, String> poll = comsumer.poll(100);
                for (ConsumerRecord<String, String> record : poll) {
                    String value = record.value();
                    Calllog log= new Calllog(value);
                    System.out.println(log);
                    hbaseDao.insert(log);
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void close() throws IOException {

    }
}
