package io.base.coreapi.controller.generics;


import io.base.coreapi.model.Pais;
import io.base.coreapi.services.generics.TipoDeDocumentoServices;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/tipo-de-documento")
@Data
@EqualsAndHashCode(callSuper=false)
public class TipoDeDocumentoController extends CrudController<Pais> {

    @Autowired
    private TipoDeDocumentoServices realServices;

    @PostConstruct
    public void posContructor() {
        this.service = realServices;
    }

}
