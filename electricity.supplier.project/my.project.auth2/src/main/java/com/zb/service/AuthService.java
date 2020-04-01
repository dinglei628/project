package com.zb.service;

import com.alibaba.fastjson.JSON;
import com.zb.dto.Dto;
import com.zb.dto.DtoUtil;
import com.zb.entity.User;
import com.zb.token.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class AuthService {
    @Value("${auth.tokenValiditySeconds}")
    private long tokenValiditySeconds;
    @Value("${auth.clientId}")
    private String clientId;
    @Value("${auth.clientSecret}")
    private String clientSecret;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //根据用户名密码登录
    public Dto<AuthToken> userlogin(String username, String password) {
        System.out.println("1-----------------------------------");
        //创建自定义 token 对象
        AuthToken authToken = applyAuthToken(username, password);
        //添加token
        String val = addToken(authToken);
        return DtoUtil.returnSuccess("ok", val);
    }


    //添加token 并返回key
    public String addToken(AuthToken authToken) {
        String key = "user_token:" + authToken.getJti_token();
        //向redis 添加token
        stringRedisTemplate.boundValueOps(key).set(JSON.toJSONString(authToken), tokenValiditySeconds, TimeUnit.SECONDS);
        return key;
    }

    /**
     * 用户退出
     *
     * @param token
     * @return
     */
    public Dto loginOut(String token) {
        if (stringRedisTemplate.hasKey(token)) {
            stringRedisTemplate.delete(token);
            return DtoUtil.returnSuccess("ok");
        } else {
            return DtoUtil.returnFail("fail", "001");
        }
    }

    /**
     * 获取用户信息
     *
     * @param token
     * @return
     */
    public User getUserInfo(String token) {
        token="user_token:"+token;
        User user = null;
        if (stringRedisTemplate.hasKey(token)) {
            String AuthTokenJson = stringRedisTemplate.opsForValue().get(token);
            AuthToken authToken = JSON.parseObject(AuthTokenJson, AuthToken.class);
            if (null != authToken) {
                String longToken = authToken.getAccess_token();
                String publicKey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnASXh9oSvLRLxk901HANYM6KcYMzX8vFPnH/To2R+SrUVw1O9rEX6m1+rIaMzrEKPm12qPjVq3HMXDbRdUaJEXsB7NgGrAhepYAdJnYMizdltLdGsbfyjITUCOvzZ/QgM1M4INPMD+Ce859xse06jnOkCUzinZmasxrmgNV3Db1GtpyHIiGVUY0lSO1Frr9m5dpemylaT0BV3UwTQWVW9ljm6yR3dBncOdDENumT5tGbaDVyClV0FEB1XdSKd7VjiDCDbUAUbDTG1fm3K9sx7kO1uMGElbXLgMfboJ963HEJcU01km7BmFntqI5liyKheX+HBUCD4zbYNPw236U+7QIDAQAB-----END PUBLIC KEY-----";
                Jwt jwt = JwtHelper.decodeAndVerify(longToken, new RsaVerifier(publicKey));
                String data = jwt.getClaims();
                user = JSON.parseObject(data, User.class);
            } else {
                return null;
            }
        }

        return user;
    }


    /**
     * 生成token
     *
     * @param username 用户的用户名
     * @param pwd      用户的密码
     * @return 发送远程地址给认证创建， 并将返回的结果封装成用户的AutoToken对象
     */
    public AuthToken applyAuthToken(String username, String pwd) {
        AuthToken authToken = new AuthToken();
        String url = "http://localhost:40400/oauth/token";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        String httpbasic = this.httpBasic("XcWebApp", "XcWebApp");
        //yy     System.out.println("httpbasic:" + httpbasic);
        headers.add("Authorization", httpbasic);
        MultiValueMap<String, String> bodys = new LinkedMultiValueMap<String, String>();
        bodys.add("grant_type", "password");
        bodys.add("username", username);
        bodys.add("password", pwd);
        bodys.add("redirect_uri", "http://localhost");
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(bodys, headers);
        ResponseEntity<Map> exchange = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Map.class);
        Map body = exchange.getBody();
        authToken.setAccess_token(body.get("access_token").toString());
        authToken.setRefresh_token(body.get("refresh_token").toString());
        authToken.setJti_token(body.get("jti").toString());
        return authToken;
    }

    /**
     * 置换touken
     *
     * @param token
     * @return
     */
    public Dto changetoken(String token) {
        String AuthTokenJson = stringRedisTemplate.opsForValue().get(token);
        AuthToken authTokenForRedis = JSON.parseObject(AuthTokenJson, AuthToken.class);
        String refresh_token = authTokenForRedis.getRefresh_token();
        AuthToken authToken = new AuthToken();
        String url = "http://localhost:40400/oauth/token";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        String httpbasic = this.httpBasic("XcWebApp", "XcWebApp");
        headers.add("Authorization", httpbasic);
        MultiValueMap<String, String> bodys = new LinkedMultiValueMap<String, String>();
        bodys.add("grant_type", "refresh_token");
        //bodys.add("redirect_uri", "http://localhost");
        bodys.add("refresh_token", refresh_token);
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(bodys, headers);
        ResponseEntity<Map> exchange = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Map.class);
        Map body = exchange.getBody();
        authToken.setAccess_token(body.get("access_token").toString());
        authToken.setRefresh_token(body.get("refresh_token").toString());
        authToken.setJti_token(body.get("jti").toString());
        String s = addToken(authToken);
        stringRedisTemplate.delete(token);
        System.out.println("置换成功");
        return DtoUtil.returnSuccess("ok", s);
    }

    private String httpBasic(String client, String secret) {
        String str = client + ":" + secret;
        byte[] encode = Base64Utils.encode(str.getBytes());
        return "Basic " + new String(encode);
    }

}
