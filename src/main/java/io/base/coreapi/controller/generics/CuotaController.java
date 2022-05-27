package io.base.coreapi.controller.generics;


import io.base.coreapi.model.Cuota;
import io.base.coreapi.services.generics.CuotaServices;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/cuota")
@Data
@EqualsAndHashCode(callSuper=false)
public class CuotaController extends CrudController<Cuota> {

    @Autowired
    private CuotaServices realServices;

    @PostConstruct
    public void posContructor() {
        this.service = realServices;
    }

}
