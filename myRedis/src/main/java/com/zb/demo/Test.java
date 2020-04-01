package com.zb.demo;

import redis.clients.jedis.Jedis;

public class Test {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("ok");
        System.out.println("连接成功");


        //基本操作
/*        jedis.set("holle","大家好");
//        jedis.expire("holle",20);
        String holle = jedis.get("holle");
        System.out.println(holle);
        Boolean bo = jedis.exists("holle");
        System.out.println("删除以前的"+bo);
        jedis.del("holle");
        System.out.println("删除以后的"+bo);*/
    }
}
