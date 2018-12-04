package com.alex.ct.common.bean;

import java.io.Closeable;

public interface Comsumer extends Closeable {


    /**
     * 消费数据
     */
    public void comsumer();
}
