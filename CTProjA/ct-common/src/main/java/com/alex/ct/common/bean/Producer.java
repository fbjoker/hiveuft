package com.alex.ct.common.bean;

import java.io.Closeable;

public interface Producer extends Closeable {


    public  void setIn(DataIn in);
    public  void setOut(DataOut out);

    /**
     * 生产数据
     */
    public void Produce();
}
