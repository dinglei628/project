package com.zb.controller;

import com.zb.entity.TbContent;
import com.zb.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TbContentController {

    @Autowired
    private TbContentService tbContentService;

    @GetMapping("/contents")
    public TbContent show(){
        return tbContentService.selectByPrimaryKey(28L);
    }
}
