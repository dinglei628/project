package com.zb.service;

//import com.zb.entity.UserJwt;
//import com.zb.entity.XcMenu;

import com.zb.entity.UserJwt;
import com.zb.feign.UserFeignClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ClientDetailsService clientDetailsService;
    @Autowired
    private UserFeignClient userFeignClient;

    /*   @Override
       public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
           //取出身份，如果身份为空说明没有认证
           Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
           //没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
           if (authentication == null) {
               ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
               if (clientDetails != null) {
                   //密码
                   System.out.println("----------------1");
                   String clientSecret = clientDetails.getClientSecret();
                   return new User(username, clientSecret, AuthorityUtils.commaSeparatedStringToAuthorityList(""));
               }
           }
           System.out.println("----------------2");
           if (StringUtils.isEmpty(username)) {
               return null;
           }
           //从xcUserFeignClient中获得用户信息
           com.zb.entity.User userext = userFeignClient.getCurrentUserByPhone(username);
           System.out.println("xcUserExt信息：" + userext.getId() + userext.getUsername() + userext.getUserpwd());
           if (userext == null) {
               return null;
           }
           //取出正确密码（hash值）
           String password = userext.getUserpwd();
           System.out.println("----------------3");
           //从数据库获取权限
   //        List<XcMenu> permissions = userext.getPermissions();
           List<String> user_permission = new ArrayList<String>();
           String user_permission_string = StringUtils.join(user_permission.toArray(), ",");
   //        for (XcMenu permission : permissions) {
   //            user_permission.add(permission.getCode());
   //        }
           //String user_permission_string = StringUtils.join(user_permission.toArray(), ",");
           *//*
        private String id;
        private String phone;
        private String email;
        private Integer phonestate;
        private Integer emailstate;
        private String username;
        private String userpwd;
        private Integer age;
        private Integer sex;
        private String permsg;
        private String vxid;
        private String qqid;
        private String picsrc;*//*
        UserJwt userDetails = new UserJwt(username,
                password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(user_permission_string));
        userDetails.setUsername(userext.getUsername());
        //  userDetails.setId(userext.getId());
      *//*  userDetails.setEmail(userext.getEmail());
        userDetails.setEmailstate(userext.getEmailstate());
        userDetails.setAge(userext.getAge());
        userDetails.setPermsg(userext.getPermsg());
        userDetails.setPhone(userext.getPhone());
        userDetails.setPhonestate(userext.getPhonestate());

        userDetails.setSex(userext.getSex());
        userDetails.setVxid(userext.getVxid());
        userDetails.setQqid(userext.getQqid());
        userDetails.setPicsrc(userext.getPicsrc());*//*


        return userDetails;
    }
*/
    @Override
    public UserDetails loadUserByUsername(String username) {
        //取出身份，如果身份为空说明没有认证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
        if (authentication == null) {
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
            System.out.println("1-----------------------");
            if (clientDetails != null) {
                //密码
                String clientSecret = clientDetails.getClientSecret();
                System.out.println("2-----------------------");
                return new User(username, clientSecret, AuthorityUtils.commaSeparatedStringToAuthorityList(""));
            }
        }
        if (StringUtils.isEmpty(username)) {
            System.out.println("3---------------------------");
            return null;
        }
        //从xcUserFeignClient中获得用户信息
        com.zb.entity.User userext = userFeignClient.getCurrentUserByPhone(username);
        System.out.println("xcUserExt信息：" + userext.getId() + userext.getUsername() + userext.getUserpwd());
        if (userext == null) {
            return null;
        }
        //取出正确密码（hash值）
        String password = userext.getUserpwd();
        //从数据库获取权限
        //List<XcMenu> permissions = userext.getPermissions();
        List<String> user_permission = new ArrayList<String>();
        /*for (XcMenu permission : permissions) {
            user_permission.add(permission.getCode());
        }*/
        String user_permission_string = StringUtils.join(user_permission.toArray(), ",");
        UserJwt userDetails = new UserJwt(username,
                password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(user_permission_string));
        userDetails.setUsername(userext.getUsername());
        userDetails.setId(userext.getId());
        userDetails.setEmail(userext.getEmail());
        userDetails.setEmailstate(userext.getEmailstate());
        userDetails.setAge(userext.getAge());
        userDetails.setPermsg(userext.getPermsg());
        userDetails.setPhone(userext.getPhone());
        userDetails.setPhonestate(userext.getPhonestate());
        userDetails.setSex(userext.getSex());
        userDetails.setVxid(userext.getVxid());
        userDetails.setQqid(userext.getQqid());
        userDetails.setPicsrc(userext.getPicsrc());
        return userDetails;
    }

}
