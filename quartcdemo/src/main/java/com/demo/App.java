package com.demo;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SchedulerException {
       test1();

    }
    public static void test2()throws SchedulerException{
        JobDetail job = JobBuilder.newJob(HolleJop.class).withIdentity("holleJob").build();
        String exp = "0 48 17 1 11 ?";
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(exp);
        CronTrigger trigger = TriggerBuilder.newTrigger().
                withIdentity("myTrigger").withSchedule(scheduleBuilder).build();
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.scheduleJob(job,trigger);
        scheduler.start();

    }

    public static void test1()throws SchedulerException{
        JobDetail job = JobBuilder.newJob(HolleJop.class).withIdentity("holleJob").build();
        SimpleScheduleBuilder scheduleBuilder =
                SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever();
        SimpleTrigger trigger =
                TriggerBuilder.newTrigger().withIdentity("myTrigger").withSchedule(scheduleBuilder).build();
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.scheduleJob(job,trigger);
        scheduler.start();
    }
}
