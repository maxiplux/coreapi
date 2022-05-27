package io.base.coreapi.controller.generics;


import io.base.coreapi.model.EmailType;
import io.base.coreapi.services.generics.EmailTypeServices;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/email-type")
@Data
@EqualsAndHashCode(callSuper=false)
public class EmailTypeController extends CrudController<EmailType> {

    @Autowired
    private EmailTypeServices realServices;

    @PostConstruct
    public void posContructor() {
        this.service = realServices;
    }

}
