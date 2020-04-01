package com.zb.service.impl;

import com.zb.entity.TbPermission;
import com.zb.mapper.TbPermissionMapper;
import com.zb.service.TbPermissionService;
import com.zb.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbPermissionServiceImpl implements TbPermissionService {
    @Autowired
    private TbPermissionMapper tbPermissionMapper;

    @Override
    public List<TbPermission> findPermissionListByUser(Long uid) {
        return tbPermissionMapper.findPermissionListByUser(uid);
    }
}
