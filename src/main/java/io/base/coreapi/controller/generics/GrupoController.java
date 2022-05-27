package io.base.coreapi.controller.generics;


import io.base.coreapi.model.Grupo;
import io.base.coreapi.services.generics.GrupoServices;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/grupo")
@Data
@EqualsAndHashCode(callSuper=false)
public class GrupoController extends CrudController<Grupo> {

    @Autowired
    private GrupoServices realServices;

    @PostConstruct
    public void posContructor() {
        this.service = realServices;
    }

}
