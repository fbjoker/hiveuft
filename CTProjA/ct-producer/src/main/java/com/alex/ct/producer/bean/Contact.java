package com.alex.ct.producer.bean;

import com.alex.ct.common.bean.Data;

public class Contact extends Data {

    private  String tel;
    private  String name;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setvalue(Object val) {

        content = (String) val;

        String[] split = content.split("\t");
        tel=split[0];
        name=split[1];
    }

    @Override
    public String toString() {
        return tel + '\t' + name + '\t' ;
    }
}
