package com.alex.test.demo1;


import kafka.api.FetchRequest;
import kafka.api.FetchRequestBuilder;
import kafka.cluster.BrokerEndPoint;
import kafka.javaapi.*;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.javaapi.message.ByteBufferMessageSet;
import kafka.message.MessageAndOffset;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class ConsumerApi {
    @SuppressWarnings("all")
    public static void main(String[] args) throws UnsupportedEncodingException {

        BrokerEndPoint leader=null;
        String host="hadoop102";
        int port=9092;
        SimpleConsumer mconsumer = new SimpleConsumer(host,port,500,10*1024,"m");
        TopicMetadataRequest request = new TopicMetadataRequest(Arrays.asList("demo1"));

        TopicMetadataResponse response = mconsumer.send(request);

        for (TopicMetadata topicsMetadatum : response.topicsMetadata()) {
            if ("demo1".equals(topicsMetadatum.topic())){
                for (PartitionMetadata partion : topicsMetadatum.partitionsMetadata()) {
                    int i = partion.partitionId();
                    if (i==1){

                        leader = partion.leader();
                        System.out.println(leader);
                    }

                }

            }


        }

        SimpleConsumer simpleConsumer = new SimpleConsumer(leader.host(), leader.port(), 500, 10 * 1024, "c2");

        FetchRequest build = new FetchRequestBuilder().addFetch("demo1",1,0,10*1024).build();

        FetchResponse fetch = simpleConsumer.fetch(build);

        ByteBufferMessageSet msg = fetch.messageSet("demo1", 1);

        for (MessageAndOffset messageAndOffset : msg) {

            ByteBuffer payload = messageAndOffset.message().payload();
            byte[] bs = new byte[payload.limit()];
            ByteBuffer bb = payload.get(bs);


            System.out.println(new String(bs,"UTF-8"));
//            System.out.println(bb);
//            System.out.println(bs);


        }



    }

}
