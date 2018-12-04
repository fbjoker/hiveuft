package com.alex.ct.comsumer.bean;

import com.alex.ct.common.bean.Comsumer;
import com.alex.ct.common.constant.Names;
import com.alex.ct.comsumer.bean.dao.HbaseDao;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;


public class Ctcomsumer implements Comsumer {
    public void comsumer() {

        Properties prop = new Properties();
        try {
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("consumer.properties"));


            KafkaConsumer consumer = new KafkaConsumer<String, String>(prop);
            System.out.println(Names.TOPIC.getvalue());

            consumer.subscribe(Arrays.asList(Names.TOPIC.getvalue()));


            HbaseDao dao = new HbaseDao();
            dao.init();


            while (true) {
                ConsumerRecords<String, String> poll = consumer.poll(100);
                for (ConsumerRecord<String, String> record : poll) {
                    String value = record.value();
                    System.out.println(value);
                    //插入数据
                    //dao.insert(value);
                    Calllog calllog = new Calllog(value);
                    dao.insert(calllog);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void close() throws IOException {

    }
}
