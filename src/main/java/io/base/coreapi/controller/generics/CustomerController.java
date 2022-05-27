package io.base.coreapi.controller.generics;


import io.base.coreapi.model.Customer;
import io.base.coreapi.services.generics.CustomerServices;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/customer")
@Data
@EqualsAndHashCode(callSuper=false)
public class CustomerController extends CrudController<Customer> {

    @Autowired
    private CustomerServices realServices;

    @PostConstruct
    public void posContructor() {
        this.service = realServices;
    }

}
