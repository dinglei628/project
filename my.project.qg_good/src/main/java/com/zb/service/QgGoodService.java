package com.zb.service;

import com.zb.dto.Dto;
import com.zb.pojo.qg_good_infrom;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.List;

public interface QgGoodService {
    public String initGoodsToRedisById(Integer qg_id) throws  Exception;

    public int checkGoods(String goodsId) throws  Exception;

    public int lockGoods(String goodsId , String userId) throws  Exception;

    public com.zb.dto.Dto qg(String token , String goodsId) throws Exception;


    List<qg_good_infrom> getQgGoodBy();
}
