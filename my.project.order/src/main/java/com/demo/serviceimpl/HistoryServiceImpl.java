package com.demo.serviceimpl;

import com.demo.entity.History;
import com.demo.mapper.HistoryMapper;
import com.demo.service.HistoryService;
import com.demo.service.OrderService;
import com.zb.dto.Dto;
import com.zb.dto.DtoUtil;
import com.zb.dto.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HistoryServiceImpl implements HistoryService {
    @Autowired(required = false)
    private HistoryMapper historyMapper;

    @Override
    public Dto searchHistory(@PathVariable("token") String token, @RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex,
                             @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize) {
        if(token == "" || token == null){
            return DtoUtil.returnFail("用户token不能为空", OrderService.ERROR_USER_NOT);
        }
        String uid = token;
        Integer count = historyMapper.getHisOrderByUserIdCount(uid);
        List<History> list = historyMapper.getHisOrderByUserId(uid, (pageIndex - 1) * pageSize, pageSize);
        Page<History> page = new Page(pageIndex,pageSize,count,list);
        return DtoUtil.returnSuccess("success",page);
    }

    @Override
    public Dto searchHistoryCondition(@RequestParam(value = "uid",required = false) String uid,
                                      @RequestParam(value = "gid",required = false)String gid,
                                      @RequestParam(value = "startDate",required = false)String startDate,
                                      @RequestParam(value = "endDate",required = false)String endDate,
                                      @RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex,
                                      @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize) {

        Integer count = historyMapper.getHisOrderbyConditionCount(uid,gid,startDate,endDate);
        List<History> list = historyMapper.getHisOrderbyCondition(uid,gid,startDate,endDate,(pageIndex - 1) * pageSize, pageSize);
        Page<History> page = new Page(pageIndex,pageSize,count,list);
        return DtoUtil.returnSuccess("success",page);
    }
}
