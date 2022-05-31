package io.base.coreapi.controllers;


import io.base.coreapi.CoreapiApplication;
import io.base.coreapi.config.CrudAbstract;
import io.base.coreapi.model.Benefactor;
import io.base.coreapi.repositories.BenefactorRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CoreapiApplication.class)
@Slf4j
@DisplayName("BenefactorControllerAuthTest.")
public class BenefactorControllerAuthTest extends CrudAbstract {


    @Autowired
    private BenefactorRepository benefactorRepository;

    @Override
    public void extraTearDownSteps() {
        log.info("Extra settings here extraTearDownSteps");
        bodyForUpdate = new HashMap<>();
        bodyForCreate = new HashMap<>();
    }

    @Override
    public void extraSetupSteps() {
        this.baseUrl="/api/v1/benefactor/";
        log.info("Extra settings here extraSetupSteps");
    }

    @SneakyThrows
    @Test
    @Override
    public void createElement() {
        log.info("createElement");
        Optional<Benefactor> optionalBenefactor =benefactorRepository.findById(1L);
        Benefactor benefactor=optionalBenefactor.get();
        benefactor.setId(null);
        benefactor.setActivo(false);
        benefactor.setRazonSocial("Este es un integration test");
        this.mockMvc.perform( MockMvcRequestBuilders
                        .post(this.baseUrl )
                        .content(asJsonString(benefactor))
                        .headers(this.headers)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());


    }
    @SneakyThrows
    @Test
    @Override
    public void updateElement() {
        log.info("updateElement");
        Optional<Benefactor> optionalBenefactor =benefactorRepository.findById(1L);
        Benefactor benefactor=optionalBenefactor.get();
        benefactor.setId(null);
        benefactor.setActivo(false);
        benefactor.setRazonSocial("I changed it using put");
        this.mockMvc.perform( MockMvcRequestBuilders
                        .put(this.baseUrl+"{id}/", 1 )
                        .content(asJsonString(benefactor))
                        .headers(this.headers)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    @Override
    public void testGetElements() {

        log.info("testGetElements");
        this.mockMvc.perform( MockMvcRequestBuilders
                        .get(this.baseUrl )
                        .headers(this.headers)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());


    }

}
