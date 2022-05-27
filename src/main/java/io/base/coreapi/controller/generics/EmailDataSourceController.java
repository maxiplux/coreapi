package io.base.coreapi.controller.generics;


import io.base.coreapi.model.EmailDataSource;
import io.base.coreapi.services.generics.EmailDataSourceServices;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/email-data-source")
@Data
@EqualsAndHashCode(callSuper=false)
public class EmailDataSourceController extends CrudController<EmailDataSource> {

    @Autowired
    private EmailDataSourceServices realServices;

    @PostConstruct
    public void posContructor() {
        this.service = realServices;
    }

}
