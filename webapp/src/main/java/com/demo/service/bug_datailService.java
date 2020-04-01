package com.demo.service;

import com.demo.pojo.Bug_detail;
import com.demo.pojo.Bug_pro;

import java.util.List;

public interface bug_datailService {
    List<Bug_detail> getListBug(int projectId);

    List<Bug_pro> getListBug_Por();

    int insert(Bug_detail b);
}
