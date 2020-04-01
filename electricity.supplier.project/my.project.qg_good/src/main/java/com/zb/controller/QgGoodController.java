package com.zb.controller;

import com.zb.dto.Dto;
import com.zb.dto.DtoUtil;
import com.zb.pojo.qg_good_infrom;
import com.zb.service.QgGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Component
@RestController
public class QgGoodController {

    @Autowired
    private QgGoodService qgGoodService;

    @GetMapping("/initQgGoods/{qg_id}")
    public String initQgGoods(@PathVariable("qg_id") Integer qg_id) {
        try {
            return qgGoodService.initGoodsToRedisById(qg_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    @GetMapping("/qg/{token}/{goodsId}")
    public Dto qg(@PathVariable("token") String token , @PathVariable("goodsId") String goodsId){
        try {
            return qgGoodService.qg(token,goodsId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DtoUtil.returnFail("服务异常","66666");
    }


    @Scheduled(cron = "0/60 * * * * ?")
    @GetMapping("getQgGoodBy")
    public List<qg_good_infrom> getQgGoodBy(){
        return qgGoodService.getQgGoodBy();
    }
}
