package com.zb.service;

import com.zb.bug_detail;
import com.zb.bug_project;

import java.util.List;

public interface DetailService {

    List<bug_detail> getDetailList(Integer projectId);

    List<bug_project> getSelect();

    int insert(Integer projectId,Integer severity,String title,String reportUser);
}
