package com.alex.test.demo1.demo1c;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class PatitionTestc implements Partitioner {
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        return 0;
    }

    public void close() {

    }

    public void configure(Map<String, ?> configs) {

    }
}
