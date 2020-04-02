package com.zb.service;

import com.zb.dto.Dto;
import com.zb.dto.Page;
import com.zb.pojo.Video_data;
import com.zb.pojo.Videoaddress;
import com.zb.pojo.Videotype;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodService {
    Dto getVideoById(Integer id, Integer pageSize, String token);

    List<Videoaddress> getVideoAddRess(Integer videoTypeId);

    public Page<Video_data> findVideoByPage(Integer index, Integer size, String key) throws Exception;


    List<Video_data> getTypeSel(Integer typeId);

}
