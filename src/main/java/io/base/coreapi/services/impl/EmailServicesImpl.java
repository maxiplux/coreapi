package io.base.coreapi.services.impl;


import freemarker.template.TemplateException;
import io.base.coreapi.services.EmailServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class EmailServicesImpl implements EmailServices {

    private final AtomicInteger count = new AtomicInteger();
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private FreeMarkerConfigurer configuration;


//    @Override
//    public void sendEmail(String subject, String context) {
//
//        SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setTo("to_1@gmail.com", "maxiplux@gmail.com", "to_3@yahoo.com");
//
//        msg.setSubject(subject);
//        msg.setText(context);
//
//        javaMailSender.send(msg);
//
//    }

    @Override
    public void executeSendEmailRuleById(String subject) {

        this.sendEmail("Esto es un demo", "contexto");

    }

    @Override
    public void sendEmail(String subject, String context) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);


        String emailContent = null;
        try {
            helper.setSubject(subject);
            helper.addBcc("maxiplux@gmail.com");
            emailContent = getEmailContent();
            helper.setText(emailContent, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Exception sending message {}", e.getMessage());
        } catch (IOException e) {
            log.error("IOException sending message {}", e.getMessage());
        } catch (TemplateException e) {
            log.error("TemplateException sending message {}", e.getMessage());
        }


    }

    String getEmailContent() throws IOException, TemplateException {

        Map<String, Object> model = new HashMap<>();
        model.put("user", "Juan");
        String templateContent = FreeMarkerTemplateUtils
            .processTemplateIntoString(configuration.getConfiguration()
                    .getTemplate("welcome.ftlh"),
                model);


        return templateContent;
    }

}
