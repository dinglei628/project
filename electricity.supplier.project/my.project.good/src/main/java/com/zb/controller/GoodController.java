package com.zb.controller;

import com.zb.dto.Page;
import com.zb.mapper.GoodMapper;
import com.zb.pojo.Video_data;
import com.zb.pojo.Videoaddress;
import com.zb.pojo.Videotype;
import com.zb.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GoodController {

    @Autowired(required = false)
    GoodService goodService;
    @Autowired(required = false)
    GoodMapper goodMapper;


    @GetMapping("show/{id}/{pageSize}")
    public List<Videoaddress> show(@PathVariable("id")Integer id,@PathVariable("pageSize")Integer pageSize){
        Video_data videoById = goodService.getVideoById(id, pageSize);

        return goodService.getVideoAddRess(videoById.getVideoTypeId());
    }


    public List<Videoaddress> videoList(@PathVariable("videoTypeId")Integer videoTypeId){
        return goodService.getVideoAddRess(videoTypeId);
    }


    @GetMapping("ESShow")
    public Page<Video_data> ESShow(@RequestParam(value = "index",defaultValue = "1") Integer index,
                                   @RequestParam(value = "key",required = false) String key) throws Exception {
        Integer size = 5;
        Page<Video_data> videoByPage = goodService.findVideoByPage(index, size, key);
        return videoByPage;
    }


    @GetMapping("getTypeSel/{typeId}")
    public List<Video_data> getTypeSel(@PathVariable("typeId")Integer typeId){
        return goodService.getTypeSel(typeId);
    }

}
