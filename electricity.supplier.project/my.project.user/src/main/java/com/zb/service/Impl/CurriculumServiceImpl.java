package com.zb.service.Impl;

import com.zb.dto.Dto;
import com.zb.dto.DtoUtil;
import com.zb.dto.Page;
import com.zb.entity.Curriculum;
import com.zb.mapper.CurriculumMapper;
import com.zb.service.CurriculumService;
import com.zb.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurriculumServiceImpl implements CurriculumService {

    @Autowired
    CurriculumMapper curriculumMapper;


    @Override
    public Dto<List<Curriculum>> findCurriclumByUserId(String token, Integer pageIndex) {
        Integer count = curriculumMapper.getCurriculumCountByUid("754118266611306496");
        Integer from = (pageIndex - 1) * PageUtil.pageSize;
        List<Curriculum> curriculumsList = curriculumMapper.getCurriculumsByUid("754118266611306496", from, PageUtil.pageSize);
        Page<Curriculum> page = new Page<>(pageIndex, PageUtil.pageSize, count, curriculumsList);
        return DtoUtil.returnSuccess("ok", page);
    }
}
