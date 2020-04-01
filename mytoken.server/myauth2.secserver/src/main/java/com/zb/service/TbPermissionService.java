package com.zb.service;

import com.zb.entity.TbPermission;

import java.util.List;

public interface TbPermissionService {
    public List<TbPermission> findPermissionListByUser(Long uid);
}
