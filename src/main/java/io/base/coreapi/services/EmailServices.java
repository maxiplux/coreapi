package io.base.coreapi.services;

public interface EmailServices {

    void sendEmail(String subject, String context);

    void executeSendEmailRuleById(String subject);


}
