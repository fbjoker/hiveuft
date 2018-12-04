package com.alex.ct.produer.bean;

import com.alex.ct.bean.Data;

public class Contact extends Data {
        private  String tel;
        private  String name;

    public Contact() {
    }

    public Contact(String tel, String name) {
        this.tel = tel;
        this.name = name;
    }

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


    public void setVal(Object str) {
       content= (String)str;
        String[] split = content.split("\t");
        tel=split[0];
        name=split[1];
    }



    @Override
    public String toString() {
        return "Contact{" +
                "tel='" + tel + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
