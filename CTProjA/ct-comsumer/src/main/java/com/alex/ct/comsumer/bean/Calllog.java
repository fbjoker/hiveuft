package com.alex.ct.comsumer.bean;


import com.alex.ct.common.api.Column;
import com.alex.ct.common.api.RowKey;
import com.alex.ct.common.api.TableRef;

@TableRef("ct:calllog")
public class Calllog {

    @RowKey
    private  String rowkey;
    @Column(family = "caller",colume = "callrow")
    private String call1;
    @Column(family = "caller")
    private  String call2;
    @Column(family = "caller")
    private  String calltime;
    @Column(family = "caller")
    private  String duration;
    private  String name;

    public Calllog() {
    }

    public Calllog(String data) {
        String[] values = data.split("\t");
        call1 = values[0];
        call2 = values[1];
        calltime = values[2];
        duration = values[3];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRowkey() {
        return rowkey;
    }

    public void setRowkey(String rowkey) {
        this.rowkey = rowkey;
    }

    public String getCall1() {
        return call1;
    }

    public void setCall1(String call1) {
        this.call1 = call1;
    }

    public String getCall2() {
        return call2;
    }

    public void setCall2(String call2) {
        this.call2 = call2;
    }

    public String getCalltime() {
        return calltime;
    }

    public void setCalltime(String calltime) {
        this.calltime = calltime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

}
