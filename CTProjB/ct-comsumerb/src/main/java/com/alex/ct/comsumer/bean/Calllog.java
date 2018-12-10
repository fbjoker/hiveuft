package com.alex.ct.comsumer.bean;

public class Calllog  {
    private String rowkey;

    private  String call1;
    private  String call2;
    private  String calltime;
    private  String duration;
    private  boolean flg;

    public Calllog() {
    }

    public Calllog(String call1, String call2, String calltime, String duration) {


        this.call1 = call1;
        this.call2 = call2;
        this.calltime = calltime;
        this.duration = duration;
    }
    public Calllog(String date) {
        String[] val = date.split("/t");



        this.call1 = val[0];
        this.call2 = val[1];
        this.calltime = val[2];
        this.duration = val[3];
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

    public boolean isFlg() {
        return flg;
    }

    public void setFlg(boolean flg) {
        this.flg = flg;
    }

    @Override
    public String toString() {
        return call1 + '\t' + call2 + '\t'  + calltime + '\t' +  duration + '\t'+flg ;
    }
}
