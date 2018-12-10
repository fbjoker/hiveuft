package com.alex.ct.cacha;

import com.alex.ct.common.util.JDBCUtil;
import redis.clients.jedis.Jedis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BootStrap {

    public static void main(String[] args) {

        Map<String, Integer> user = new HashMap<String, Integer>();
        Map<String, Integer> date = new HashMap<String, Integer>();


        Connection conn = null;

        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {

            conn = JDBCUtil.getConn();
            String sqluser = "select tel,id from ct_user  ";
            ps = conn.prepareStatement(sqluser);


            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String tel = resultSet.getString(1);
                Integer id = resultSet.getInt(2);

                user.put(tel, id);

            }


            sqluser = "select id,year,month,day from ct_date  ";
            ps = conn.prepareStatement(sqluser);


            resultSet = ps.executeQuery();
            while (resultSet.next()) {

                Integer id = resultSet.getInt(1);
                String year = resultSet.getString(2);
                String month = resultSet.getString(3);
                if (month.length() == 1) {
                    month = "0" + month;
                }
                String day = resultSet.getString(4);
                if (day.length() == 1) {
                    day = "0" + month;
                }

                date.put(year + month + day, id);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {

                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        Jedis jedis = new Jedis("hadoop106", 6379);

        Iterator<String> iterator = user.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Integer val = user.get(key);


            jedis.hset("ct_user", key, "" + val);

        }


        iterator = date.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Integer val = date.get(key);


            jedis.hset("ct_date", key, "" + val);

        }


    }
}
