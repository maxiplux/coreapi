package io.base.coreapi.jobs;


import io.base.coreapi.services.EmailServices;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author sotobotero
 */
@Component
@DisallowConcurrentExecution
@Slf4j
public class EmailJob implements Job {

    @Autowired
    private EmailServices emailServices;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        log.info("Job ** {} ** fired @ {}", context.getJobDetail().getKey().getName(), context.getFireTime());
        //todo:Send email.
        this.emailServices.executeSendEmailRuleById(context.getJobDetail().getKey().getName());

        log.info("Next job scheduled @ {}", context.getNextFireTime());
    }

}
