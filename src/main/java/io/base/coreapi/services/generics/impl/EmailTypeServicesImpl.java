package io.base.coreapi.services.generics.impl;


import io.base.coreapi.model.EmailType;
import io.base.coreapi.repositories.EmailTypeRepository;
import io.base.coreapi.services.generics.EmailTypeServices;
import io.base.coreapi.services.generics.cruds.CrudServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class EmailTypeServicesImpl extends CrudServicesImpl<EmailType> implements EmailTypeServices<EmailType> {

    @Autowired
    private EmailTypeRepository realRepository;


    @PostConstruct
    public void posContructor() {
        this.setRepository(realRepository);
    }


    public Optional<EmailType> UpdateById(long id, EmailType element) {
        Optional<EmailType> optionalCurrentCompany = this.repository.findById(id);
        if (optionalCurrentCompany.isPresent()) {
            EmailType currentProduct = optionalCurrentCompany.get();

            if (element.getName() != null) {
                currentProduct.setName(element.getName());
            }


            return Optional.of((EmailType) this.repository.save(currentProduct));
        }
        return Optional.empty();

    }


}
