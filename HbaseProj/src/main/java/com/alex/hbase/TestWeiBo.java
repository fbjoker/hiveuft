package com.alex.hbase;

import com.alex.hbase.controller.WeiBoController;

public class TestWeiBo {

    public static void main(String[] args) {

        WeiBoController weiBoController=new WeiBoController();


        weiBoController.init();
        System.out.println("初始化完成...");





        //发微博
        String starUser="zz";
        String fanUser="L";
        String content="testxxxxxxxxxx";

        weiBoController.publishWeiBo(starUser,content );


        //关注(star)
        weiBoController.attendUser(fanUser,starUser);



        //查看微博
        /**
         * 1.获取明星的微博(收件箱
         * 2.通过微博数据查看信息
         *
         */

        weiBoController.viewWeiBo(fanUser,starUser);



        //取消关注
        weiBoController.deleteAttend(fanUser,starUser);


    }
}
