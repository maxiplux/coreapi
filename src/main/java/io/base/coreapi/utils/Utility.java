package io.base.coreapi.utils;


import io.base.coreapi.exceptions.InvalidCronException;
import io.base.coreapi.model.EmailTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;

import java.text.MessageFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;

@Slf4j
public class Utility {


    public static String buildCron(LocalDate cronDate, LocalTime cronTime, String frequency) {

        switch (EmailTypeEnum.valueOf(frequency)) {
            case DIARIO:
                return MessageFormat.format("0 {0} {1} ? * * *", cronTime.getMinute(), cronTime.getHour());
            case MENSUAL:
                return MessageFormat.format("0 {0} {1} {2} * ? *", cronTime.getMinute(), cronTime.getHour(), cronDate.getDayOfMonth());
            case UNICA_VEZ:
                return MessageFormat.format("0 {0} {1} {2} {3} ? {4}", cronTime.getMinute(), cronTime.getHour(), cronDate.getDayOfMonth(), cronDate.getMonthValue(), "" + cronDate.getYear());

        }
        throw new InvalidCronException("Invalid Cron definition");


    }

    public static boolean isValidCronExpression(String cronExpression) {

        try {

            CronScheduleBuilder.cronSchedule(new CronExpression(cronExpression));
            return true;
        } catch (ParseException e) {
            log.error("isValidCronExpression -> ParseException {} ", e.getMessage());
        }
        return false;
    }
}
