package io.base.coreapi.controller.generics;


import io.base.coreapi.model.Benefactor;
import io.base.coreapi.services.generics.BenefactorServices;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/benefactor")
@Data
public class BenefactorController extends CrudController<Benefactor> {

    @Autowired
    private BenefactorServices realServices;

    @PostConstruct
    public void posContructor() {
        this.service = realServices;
    }

}
