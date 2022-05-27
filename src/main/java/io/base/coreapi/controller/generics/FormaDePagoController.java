package io.base.coreapi.controller.generics;


import io.base.coreapi.model.FormaDePago;
import io.base.coreapi.services.generics.FormaDePagoServices;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/forma-de-pago")
@Data
@EqualsAndHashCode(callSuper=false)
public class FormaDePagoController extends CrudController<FormaDePago> {

    @Autowired
    private FormaDePagoServices realServices;

    @PostConstruct
    public void posContructor() {
        this.service = realServices;
    }

}
