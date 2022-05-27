package io.base.coreapi.controller.generics;


import io.base.coreapi.model.Pais;
import io.base.coreapi.services.generics.PaisServices;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/pais")
@Data
@EqualsAndHashCode(callSuper=false)
public class PaisesController extends CrudController<Pais> {

    @Autowired
    private PaisServices realServices;

    @PostConstruct
    public void posContructor() {
        this.service = realServices;
    }

}
