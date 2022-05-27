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
@DisplayName("FormaDePagoControllerTest.")
public class FormaDePagoControllerTest extends CrudAbstract {




    @Override
    public void extraTearDownSteps() {
        log.info("Extra settings here extraTearDownSteps");
        bodyForUpdate = new HashMap<>();
        bodyForCreate = new HashMap<>();
    }

    @Override
    public void extraSetupSteps() {
        this.baseUrl="/api/v1/forma-de-pago/";
        this.bodyForUpdate.put("activo", "false");
        this.bodyForUpdate.put("nombre", "Update forma-de-pago 223Q4SDAF"+ UUID.randomUUID().toString().replace("-", ""));

        bodyForUpdate.put("moraMaxima", "11");
        bodyForUpdate.put("maximoDeRecordatorios", "11");


        bodyForCreate.put("activo", "true");

        bodyForCreate.put("moraMaxima", "10");
        bodyForCreate.put("maximoDeRecordatorios", "10");

        bodyForCreate.put("nombre", "forma-de-pago 223Q4SDAF"+ UUID.randomUUID().toString().replace("-", ""));

        log.info("Extra settings here extraSetupSteps");
    }
}
