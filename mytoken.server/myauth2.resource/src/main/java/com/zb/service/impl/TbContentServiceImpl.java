package com.zb.service.impl;

import com.zb.entity.TbContent;
import com.zb.mapper.TbContentMapper;
import com.zb.service.TbContentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TbContentServiceImpl implements TbContentService {

    @Resource
    private TbContentMapper tbContentMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return tbContentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TbContent record) {
        return tbContentMapper.insert(record);
    }

    @Override
    public int insertSelective(TbContent record) {
        return tbContentMapper.insertSelective(record);
    }

    @Override
    public TbContent selectByPrimaryKey(Long id) {
        return tbContentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TbContent record) {
        return tbContentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TbContent record) {
        return tbContentMapper.updateByPrimaryKey(record);
    }

}
