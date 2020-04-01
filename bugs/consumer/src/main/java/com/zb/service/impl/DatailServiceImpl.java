package com.zb.service.impl;

import com.zb.bug_detail;
import com.zb.bug_project;
import com.zb.feign.MyFeign;
import com.zb.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DatailServiceImpl implements DetailService {
    @Autowired
    MyFeign myFeign;

    @Override
    public List<bug_detail> getDetailList(Integer projectId) {
        return myFeign.getListDetail(projectId);
    }

    @Override
    public List<bug_project> getSelect() {
        return myFeign.getsel();
    }

    @Override
    public int insert(Integer projectId, Integer severity, String title, String reportUser) {
        return myFeign.insert(projectId,severity,title,reportUser);
    }


}
