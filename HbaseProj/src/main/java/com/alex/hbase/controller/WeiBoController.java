package com.alex.hbase.controller;

import com.alex.hbase.service.WeiBoService;

import java.io.IOException;

public class WeiBoController {

    private WeiBoService weiBoService = new WeiBoService();


    //初始化
    public void init() {




        //创建命名空间
        try {

            weiBoService.start();
            weiBoService.creatNamespace();
            //创建表
            weiBoService.creatTable();
            weiBoService.end();


        } catch (IOException e) {
            e.printStackTrace();
        }







    }

    public void publishWeiBo(String  userid,String content) {
        try {
            weiBoService.start();
            long time=System.currentTimeMillis();

            weiBoService.publishWeiBo(userid,time,content);
            weiBoService.end();

        }catch (Exception e){

        }

    }

    public void attendUser(String fanUser, String starUser) {

        try {
            weiBoService.start();
            long time=System.currentTimeMillis();

            weiBoService.attendUser(fanUser,starUser);
            weiBoService.end();

        }catch (Exception e){

        }


    }

    public void viewWeiBo(String fanUser, String starUser) {

        try {
            weiBoService.start();
            long time=System.currentTimeMillis();

            weiBoService.viewWeiBo(fanUser,starUser);
            weiBoService.end();

        }catch (Exception e){

        }



    }

    public void deleteAttend(String fanUser, String starUser) {
        try {
            weiBoService.start();

            weiBoService.deleteAttend(fanUser,starUser);
            weiBoService.end();

        }catch (Exception e){

        }

    }
}
