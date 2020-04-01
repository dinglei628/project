package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zb.entity.Dept;
import com.zb.listener.MyAsSyncImplete;
import com.zb.mapper.DeptMapper;
import com.zb.service.DeptService;
import com.zb.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    DeptMapper mapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private MyAsSyncImplete myAsSyncImplete;

    @Cacheable(value = "cache")
    @Override
    public List<Dept> getDeptList() {
        List<Dept> depts = null;
        if(redisUtil.hasKey("deptList")){
            System.out.println("查询redis");
            String json = redisUtil.get("deptList").toString();
            depts = JSON.parseArray(json,Dept.class);
        }else{
            System.out.println("查询数据库");
            depts =  mapper.getDeptList();
            redisUtil.set("deptList", JSON.toJSONString(depts),60);
            try {
                myAsSyncImplete.addFormDeptToRedis(depts);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//             try {
//                 System.out.println("开始执行");
//                 Thread.sleep(5000);
//                 Map<String,Object> map = new HashMap<>();
//                 for (Dept dept : depts) {
//                     map.put("id",dept.getId());
//                     map.put("deptname",dept.getDeptname());
//                     redisUtil.hmset("dept:"+dept.getId(),map);
////                     redisUtil.hset("dept:",dept.getId()+"",map+"");
//                 }
//                 System.out.println("执行结束");
//             } catch (InterruptedException e) {
//                 e.printStackTrace();
//             }
         }
        return depts;
    }
}
