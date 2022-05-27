package io.base.coreapi.controller.generics;


import io.base.coreapi.model.Departamento;
import io.base.coreapi.services.generics.DepartamentoServices;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/departamento")
@Data
public class DepartamentoController extends CrudController<Departamento> {

    @Autowired
    private DepartamentoServices realServices;

    @PostConstruct
    public void posContructor() {
        this.service = realServices;
    }

}
