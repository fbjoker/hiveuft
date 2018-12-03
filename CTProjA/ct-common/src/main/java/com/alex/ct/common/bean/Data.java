package com.alex.ct.common.bean;

public  abstract class Data implements Val {


    public String content;




    public void setvalue(Object val) {
        content = (String) val;
    }

    public String getvalue() {
        return null;
    }
}
