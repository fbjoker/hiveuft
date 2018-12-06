package com.alex.ct.constant;


import com.alex.ct.bean.Val;

public enum Names implements Val {
    NAMESPACE("ct"),
    TABLE("ct:calllog"),
    CF_CALLER("caller"),
    CF_CALLEE("callee"),
    CF_INFO("info"),
    TOPIC("ct");

    private  String  name;
    private Names(String name){
        this.name =name;
    }

    ;

    public Object value() {
        return name;
    }

    public void setvalue(Object val) {

    }

    public String getvalue() {
        return name;
    }
}
