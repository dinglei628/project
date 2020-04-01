package com.zb.listener;

import com.zb.entity.Dept;
import com.zb.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class MyAsSyncImplete {
    @Resource
    private RedisUtil redisUtil;
    @Async
    public void addFormDeptToRedis(List<Dept> depts) throws InterruptedException {
        log.info("开始执行添加部门到redis");
        Thread.sleep(5000);
        for (Dept dept : depts) {
            Map<String,Object> deptMap = new HashMap<>();
            deptMap.put("id",dept.getId());
            deptMap.put("deptname",dept.getDeptname());
            redisUtil.hmset("dept:"+dept.getId(),deptMap);
        }
        log.info("结束执行添加部门到redis");
    }
}
