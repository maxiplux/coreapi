package io.base.coreapi.controllers;


import io.base.coreapi.CoreapiApplication;
import io.base.coreapi.config.CrudAbstract;
import io.base.coreapi.model.Departamento;
import io.base.coreapi.repositories.DepartamentoRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CoreapiApplication.class)
@Slf4j
@DisplayName("MunicipioControllerAuthTest.")
public class MunicipioControllerAuthTest extends CrudAbstract {

    @Autowired
    private DepartamentoRepository departamentoRepository;


    @Override
    public void extraTearDownSteps() {
        log.info("Extra settings here extraTearDownSteps");
        bodyForUpdate = new HashMap<>();
        bodyForCreate = new HashMap<>();
    }

    @Override
    public void extraSetupSteps() {
        Departamento departamento= Lists.newArrayList(this.departamentoRepository.findAll()).get(0);
        this.baseUrl="/api/v1/municipio/";
        this.bodyForUpdate.put("activo", "false");
        this.bodyForUpdate.put("nombre", "Update COLOMBINO 223Q4SDAF"+ UUID.randomUUID().toString().replace("-", ""));

        Map<String, Object>  departamentoMap = new HashMap<>();
        departamentoMap.put("id", departamento.getId());

        this.bodyForCreate.put("activo", "true");
        this.bodyForCreate.put("departamento", departamentoMap);
        this.bodyForCreate.put("nombre", "COLOMBINO 223Q4SDAF"+ UUID.randomUUID().toString().replace("-", ""));

        this.bodyForUpdate.put("departamento",  departamento);

        log.info("Extra settings here extraSetupSteps");
    }
}
