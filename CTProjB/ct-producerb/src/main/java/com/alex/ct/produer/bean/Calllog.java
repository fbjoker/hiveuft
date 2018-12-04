package com.alex.ct.produer.bean;

public class Calllog {
    private String call1;
    private  String call2;
    private  String cTime;
    private String calltime;


    public Calllog(String call1, String call2, String cTime, String calltime) {
        this.call1 = call1;
        this.call2 = call2;
        this.cTime = cTime;
        this.calltime = calltime;
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

    public String getCalltime() {
        return calltime;
    }

    public void setCalltime(String calltime) {
        this.calltime = calltime;
    }

    @Override
    public String toString() {
        return call1 + '\t' + call2 + '\t' + cTime + '\t' + calltime ;
    }
}
