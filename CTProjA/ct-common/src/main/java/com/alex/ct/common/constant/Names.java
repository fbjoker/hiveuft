package com.alex.ct.common.constant;

import com.alex.ct.common.bean.Val;

public enum Names implements Val {
    NAMESPACE("ct");

    private  String  name;
    private  Names(String name){
        this.name =name;
    }

    ;

    public Object value() {
        return name;
    }

    public void setvalue(Object val) {

    }

    public Object getvalue() {
        return null;
    }
}
