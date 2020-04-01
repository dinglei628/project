package com.zb;

import com.zb.entity.TbPermission;
import com.zb.entity.TbUser;
import com.zb.service.TbPermissionService;
import com.zb.service.TbUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import java.util.List;


@SpringBootApplication
@EnableAuthorizationServer
public class MyAutoApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MyAutoApp.class, args);
        TbUserService userService = run.getBean(TbUserService.class);
        TbPermissionService permissionService = run.getBean(TbPermissionService.class);
        TbUser user = userService.findUserLogin("manager");
        List<TbPermission> permissionListByUser = permissionService.findPermissionListByUser(user.getId());
        for (TbPermission tbPermission : permissionListByUser) {
            System.out.println(tbPermission.getName());
        }
    }
}
