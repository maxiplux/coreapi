package io.base.coreapi.services;

import org.quartz.SchedulerException;

public interface JobServices {


    String scheduleJob(String name, String cronScheduleExpression) throws SchedulerException;

    boolean deleteJob(String name);
}
