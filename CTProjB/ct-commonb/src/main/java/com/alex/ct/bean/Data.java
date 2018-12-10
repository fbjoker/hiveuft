package com.alex.ct.bean;

public class Data implements Val{
    public String content;

    public Object getVal() {
        return content;
    }

    public void setVal(Object val) {
        content= (String) val;

    }

    public void setvalue(Object val) {

    }

    public Object getvalue() {
        return null;
    }
}
