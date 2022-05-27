package io.base.coreapi.services.generics.impl;


import io.base.coreapi.model.Customer;
import io.base.coreapi.repositories.CustomerRepository;
import io.base.coreapi.services.generics.CustomerServices;
import io.base.coreapi.services.generics.cruds.CrudServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class CustomerServicesImpl extends CrudServicesImpl<Customer> implements CustomerServices<Customer> {

    @Autowired
    private CustomerRepository realRepository;


    @PostConstruct
    public void posContructor() {
        this.setRepository(realRepository);
    }


    public Optional<Customer> UpdateById(long id, Customer element) {
        Optional<Customer> optionalCurrentCompany = this.repository.findById(id);
        if (optionalCurrentCompany.isPresent()) {
            Customer currentProduct = optionalCurrentCompany.get();

            if (element.getName() != null) {
                currentProduct.setName(element.getName());
            }


            return Optional.of((Customer) this.repository.save(currentProduct));
        }
        return Optional.empty();

    }


}
