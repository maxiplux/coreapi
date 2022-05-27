package io.base.coreapi.config.cron;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class SchedulerConfiguration {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ApplicationContextHolder applicationContextHolder;

    @Autowired
    private QuartzProperties quartzProperties;


    @Bean
    public SchedulerFactoryBean schedulerFactory(ApplicationContext applicationContext) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(applicationContextHolder);
        Properties properties = new Properties();
        properties.putAll(quartzProperties.getProperties());
        schedulerFactoryBean.setDataSource(dataSource);
        schedulerFactoryBean.setQuartzProperties(properties);


        return schedulerFactoryBean;


    }

    @Bean
    public Scheduler scheduler(ApplicationContext applicationContext) throws SchedulerException {
        Scheduler scheduler = schedulerFactory(applicationContext).getScheduler();
        scheduler.start();
        return scheduler;
    }
}
