package com.alex;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.Tool;

public class ETLDriver implements Tool {
    public int run(String[] args) throws Exception {
        return 0;
    }

    public void setConf(Configuration conf) {

    }

    public Configuration getConf() {
        return null;
    }
}
