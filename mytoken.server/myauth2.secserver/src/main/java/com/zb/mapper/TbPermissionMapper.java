package com.zb.mapper;

import com.zb.entity.TbPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TbPermissionMapper {
    public List<TbPermission> findPermissionListByUser(@Param("uid") Long uid);

}
