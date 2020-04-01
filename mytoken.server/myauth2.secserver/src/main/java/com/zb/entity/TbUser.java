package com.zb.entity;

import lombok.Data;

@Data
public class TbUser {
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String created;
    private String updated;
}
