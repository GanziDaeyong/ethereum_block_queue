package org.example;


import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;


public class Main {
    public static void main(String[] args) {

        RabbitMQProducer rmq = new RabbitMQProducer();
        rmq.produce();

//        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
//
//        try{
//            Scheduler scheduler = schedulerFactory.getScheduler();
//            JobDetail job = newJob(CrawlJob.class).withIdentity("sampleJobName", Scheduler.DEFAULT_GROUP).build();
//            Trigger trigger = newTrigger().withIdentity("sampleTriggerName", Scheduler.DEFAULT_GROUP).withSchedule(cronSchedule("0/5 * * * * ?")).build();
//            scheduler.scheduleJob(job, trigger);
//            scheduler.start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }





    }
}