package io.base.coreapi.controller.generics;


import io.base.coreapi.model.Municipio;
import io.base.coreapi.services.generics.MunicipioServices;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/municipio")
@Data
public class MunicipioController extends CrudController<Municipio> {

    @Autowired
    private MunicipioServices realServices;

    @PostConstruct
    public void posContructor() {
        this.service = realServices;
    }

}
