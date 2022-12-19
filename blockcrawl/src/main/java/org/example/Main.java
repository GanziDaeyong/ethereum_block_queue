package org.example;


import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;


public class Main {
    public static void main(String[] args) {

//        RabbitMQProducer rmq = new RabbitMQProducer();
//        rmq.produce();

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();

        try{
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobDetail job = newJob(CrawlJob.class).withIdentity("sampleJobName", Scheduler.DEFAULT_GROUP).build();
            Trigger trigger = newTrigger().withIdentity("sampleTriggerName", Scheduler.DEFAULT_GROUP).withSchedule(cronSchedule("0/5 * * * * ?")).build();
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
        } catch (Exception e) {
            e.printStackTrace();
        }





    }
}