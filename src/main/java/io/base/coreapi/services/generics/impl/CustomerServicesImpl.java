package io.base.coreapi.services.generics.impl;


import io.base.coreapi.model.Customer;
import io.base.coreapi.repositories.CustomerRepository;
import io.base.coreapi.services.generics.CustomerServices;
import io.base.coreapi.services.generics.cruds.CrudServicesImpl;
import io.base.coreapi.utils.Constans;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@Slf4j
public class CustomerServicesImpl extends CrudServicesImpl<Customer> implements CustomerServices<Customer> {

    @Autowired
    private CustomerRepository realRepository;


    @PostConstruct
    public void posContructor() {
        this.setRepository(realRepository);
    }


    public Optional<Customer> UpdateById(long id, Customer element) {
        Optional<Customer> optionalCurrentCompany = this.repository.findById(id);

        this.businessValidationsRules(Optional.of(element), optionalCurrentCompany, Optional.empty(), Constans.CrudOperations.UPDATE);

        if (optionalCurrentCompany.isPresent()) {
            Customer currentProduct = optionalCurrentCompany.get();

            if (element.getName() != null) {
                currentProduct.setName(element.getName());
            }


            return Optional.of((Customer) this.repository.save(currentProduct));
        }
        return Optional.empty();

    }

    @Override
    protected void businessValidationsRules(Optional<Customer> onDbElement, Optional<Customer> goalToUpdate, Optional<Long> id, Constans.CrudOperations crudOperations) {
        log.info("START Validations");
        switch (crudOperations)
        {
            case CREATE :
                log.info("Create");
            case DELETE:
                log.info("DELETE");
            case UPDATE:
                log.info("DELETE");
        }
        log.info("End Validations");
    }


}
