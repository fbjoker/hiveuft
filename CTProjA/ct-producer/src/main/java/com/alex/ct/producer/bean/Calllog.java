package com.alex.ct.producer.bean;

public class Calllog {

    private String  call1;
    private String call2;
    private  String cTime;
    private  String callTime;


    public Calllog() {
    }

    public Calllog(String call1, String call2, String cTime, String callTime) {
        this.call1 = call1;
        this.call2 = call2;
        this.cTime = cTime;
        this.callTime = callTime;
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

    public String getcTime() {
        return cTime;
    }

    public void setcTime(String cTime) {
        this.cTime = cTime;
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    @Override
    public String toString() {
        return call1 + '\t' + call2 + '\t' + cTime + '\t' + callTime ;
    }
}
