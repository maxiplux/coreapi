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
@DisplayName("GrupoControllerTest.")
public class GrupoControllerTest extends CrudAbstract {




    @Override
    public void extraTearDownSteps() {
        log.info("Extra settings here extraTearDownSteps");
        bodyForUpdate = new HashMap<>();
        bodyForCreate = new HashMap<>();
    }

    @Override
    public void extraSetupSteps() {
        this.baseUrl="/api/v1/grupo/";
        this.bodyForUpdate.put("activo", "false");
        this.bodyForUpdate.put("nombre", "Update COLOMBINO "+ UUID.randomUUID().toString().replace("-", ""));
        bodyForCreate.put("activo", "true");
        bodyForCreate.put("nombre", "COLOMBINO "+ UUID.randomUUID().toString().replace("-", ""));

        log.info("Extra settings here extraSetupSteps");
    }
}
