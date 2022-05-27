package io.base.coreapi.services.impl;


import io.base.coreapi.jobs.EmailJob;
import io.base.coreapi.services.JobServices;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JobServicesImpl implements JobServices {

    @Autowired
    private Scheduler scheduler;

    private Trigger trigger(JobDetail jobDetail, String cronScheduleExpression) {
        //"0 30 11,12,15,18,21,23 * * ?"
        log.info("Setup New Trigger {} , {}, {}", jobDetail.getKey().getName(), jobDetail.getKey().getGroup(), cronScheduleExpression);


        return TriggerBuilder.newTrigger().forJob(jobDetail)
            .withIdentity(jobDetail.getKey().getName(), jobDetail.getKey().getGroup())
            //.startAt(DateBuilder.todayAt(0, 0, 0))
            .withSchedule(CronScheduleBuilder.cronSchedule(cronScheduleExpression))
            .build();

       /* return TriggerBuilder.newTrigger().forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), jobDetail.getKey().getGroup())
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1))
                .build(); */
    }


    private JobDetail createEmailJobDetail(String identity) {
        log.info("Info new job {}", identity);
        return JobBuilder.newJob().ofType(EmailJob.class).storeDurably()
            .withIdentity(JobKey.jobKey(identity))
            .withDescription(identity)
            .build();
    }

    @Override
    public String scheduleJob(String name, String cronScheduleExpression) throws SchedulerException {
        JobDetail job = createEmailJobDetail(name);
        log.info("scheduleJob new job {} {}", name, cronScheduleExpression);
        return scheduler.scheduleJob(job, trigger(job, cronScheduleExpression)).toString();
    }

    @Override
    public boolean deleteJob(String name) {

        try {
            return scheduler.deleteJob(JobKey.jobKey(name));
        } catch (SchedulerException e) {
            log.error("scheduleJob deleteJob job {}", name);
        }
        return false;
    }


}
