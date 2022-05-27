package io.base.coreapi.controllers;


import io.base.coreapi.CoreapiApplication;
import io.base.coreapi.config.CrudAbstract;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.UUID;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CoreapiApplication.class)
@Slf4j
@DisplayName("EmailDataSourceControllerTest.")
public class EmailDataSourceControllerTest extends CrudAbstract {




    @Override
    public void extraTearDownSteps() {
        log.info("Extra settings here extraTearDownSteps");
        bodyForUpdate = new HashMap<>();
        bodyForCreate = new HashMap<>();
    }

    @Override
    public void extraSetupSteps() {
        this.baseUrl="/api/v1/email-data-source/";

        this.bodyForUpdate.put("name", "email-type 223Q4SDAF 2222 "+UUID.randomUUID().toString().replace("-", ""));
        bodyForUpdate.put("query", "select count(1) from dual");
        bodyForUpdate.put("entityName", "Query for Update 2 ");


        bodyForCreate.put("name", "email-type 223Q4SDAF"+ UUID.randomUUID().toString().replace("-", ""));
        bodyForCreate.put("query", "select * from dual");
        bodyForCreate.put("entityName", "Query for Update");


        log.info("Extra settings here extraSetupSteps");
    }
}

