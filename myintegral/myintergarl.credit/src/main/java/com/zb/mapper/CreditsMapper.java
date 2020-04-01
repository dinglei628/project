package com.zb.mapper;

import com.zb.pojo.Credit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CreditsMapper {

    Credit getCreditBy();

}
