package com.zb.jop;

import com.zb.config.RabbitConfig;
import com.zb.mapper.JobMapper;
import com.zb.pojo.Job;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class TaskJob {

    @Resource
    private JobMapper jobMapper;
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Scheduled(cron = "0/3 * * * * *")
    public   void  send(){
        List<Job> job=jobMapper.getJobAll();
        if(job.size()<=0||job==null){
            System.out.println("没有任务");
            return;
        }
        for (int i = 0; i < job.size(); i++)
            if (job.get(i).getVarsion() != 2) {
                String msg = "2," + job.get(i).getScoreid();
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                rabbitTemplate.convertAndSend(RabbitConfig.myexchage, "score.key", msg);
                System.out.println("发送成功");
            }
    }
}
