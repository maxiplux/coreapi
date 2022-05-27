package io.base.coreapi.controller.generics;


import io.base.coreapi.model.Ocupacion;
import io.base.coreapi.services.generics.OcupacionServices;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/ocupacion")
@Data
@EqualsAndHashCode(callSuper=false)
public class OcupacionController extends CrudController<Ocupacion> {

    @Autowired
    private OcupacionServices realServices;

    @PostConstruct
    public void posContructor() {
        this.service = realServices;
    }

}
