package com.zb.test;

import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public class TestMyShopping implements Serializable {

    public void addShopp(int userId, int goodsId, int num) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("ok");
        jedis.hset(userId + "", goodsId + "", num + "");
    }

    public Long addCount(int userId, int goodsId, int num) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("ok");
        Long aLong = jedis.hincrBy(userId + "", goodsId + "", num);
        return aLong;
    }

    public void selSum(int goodId) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("ok");
        jedis.hlen(goodId + "");
    }

    public Map<String, String> selCount(int userId) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("ok");
        Map<String, String> stringStringMap = jedis.hgetAll(userId + "");
        return stringStringMap;
    }


    public void SADD(int userId) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("ok");
        jedis.sadd(userId + "", 1001 + "");
        jedis.sadd(userId + "", 1002 + "");
        jedis.sadd(userId + "", 1003 + "");
        jedis.sadd(userId + "", 1004 + "");
        jedis.sadd(userId + "", 1005 + "");
    }

    public Set<String> selSMEMBERS(int userId) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("ok");
        Set<String> smembers = jedis.smembers(userId + "");
        return smembers;
    }

    public String addCounts(int userId) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("ok");
        String srandmember = jedis.srandmember(userId + "");
        String spop = jedis.spop(userId + "");
        return spop;
    }


    public static void main(String[] args) {
        TestMyShopping t = new TestMyShopping();
//        int userId = 1002;
//        int goodId = 2001;
//        t.addShopp(userId,goodId,1);
//        t.addCount(userId,goodId,2);
//        t.selSum(goodId);
//        Map<String, String> stringStringMap = t.selCount(userId);
//        for(String key :stringStringMap.keySet()){
//            System.out.println(key+"\t"+userId);
//        }
//        System.out.println(stringStringMap);
        int userId = 10011;
        t.SADD(userId);
        System.out.println(t.selSMEMBERS(userId));
        System.out.println(t.addCounts(userId));
    }
}
