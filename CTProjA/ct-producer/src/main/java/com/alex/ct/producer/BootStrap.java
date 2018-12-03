package com.alex.ct.producer;

import com.alex.ct.common.bean.Producer;
import com.alex.ct.producer.bean.LocalFileProducer;
import com.alex.ct.producer.io.LocalFileDataIn;
import com.alex.ct.producer.io.LocalFileDataOut;

import java.io.IOException;

/**
 * 启动对象
 */
public class BootStrap {
    public static void main(String[] args) throws IOException {

        //构建生产者对象

        Producer producer = new LocalFileProducer();
        producer.setIn( new LocalFileDataIn("D:\\HadoopCluster\\Hadoopdata\\ctproj\\contact.log"));

        producer.setOut(new LocalFileDataOut("D:\\HadoopCluster\\Hadoopdata\\ctproj\\call.log"));

        //生产数据
        producer.Produce();



//        关闭生产者
        producer.close();
    }
}
