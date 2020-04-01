package com.zb.service;

import com.zb.dto.Dto;
import com.zb.entity.Curriculum;

import java.util.List;

public interface CurriculumService {
    Dto<List<Curriculum>> findCurriclumByUserId(String token, Integer pageIndex);
}
