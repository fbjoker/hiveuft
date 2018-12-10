package com.alex.ct.comsumer;

import com.alex.ct.comsumer.bean.CalllogComsumer;

import java.io.IOException;

public class Bootstrapcomsumer {

    public static void main(String[] args) throws IOException {

        CalllogComsumer comsumer = new CalllogComsumer();

        comsumer.comsumer();

        comsumer.close();

    }
}
