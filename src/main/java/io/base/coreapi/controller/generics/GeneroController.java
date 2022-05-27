package io.base.coreapi.controller.generics;


import io.base.coreapi.model.Genero;
import io.base.coreapi.services.generics.GeneroServices;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/genero")
@Data
@EqualsAndHashCode(callSuper=false)
public class GeneroController extends CrudController<Genero> {

    @Autowired
    private GeneroServices realServices;

    @PostConstruct
    public void posContructor() {
        this.service = realServices;
    }

}
