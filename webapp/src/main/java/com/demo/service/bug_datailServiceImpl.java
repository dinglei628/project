package com.demo.service;

import com.demo.pojo.Bug_detail;
import com.demo.pojo.Bug_pro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class bug_datailServiceImpl implements bug_datailService{
    @Autowired
    com.demo.dao.bug_detailMapper bug_detailMapper;

    @Override
    public List<Bug_detail> getListBug(int projectId) {
        return bug_detailMapper.getListBug(projectId);
    }

    @Override
    public List<Bug_pro> getListBug_Por() {
        return bug_detailMapper.getListBug_Por();
    }

    @Override
    public int insert(Bug_detail b) {
        return bug_detailMapper.insert(b);
    }
}
