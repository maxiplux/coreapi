package io.base.coreapi.services.generics.impl;


import io.base.coreapi.model.EmailType;
import io.base.coreapi.repositories.EmailTypeRepository;
import io.base.coreapi.services.generics.EmailTypeServices;
import io.base.coreapi.services.generics.cruds.CrudServicesImpl;
import io.base.coreapi.utils.Constans;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@Slf4j
public class EmailTypeServicesImpl extends CrudServicesImpl<EmailType> implements EmailTypeServices<EmailType> {

    @Autowired
    private EmailTypeRepository realRepository;


    @PostConstruct
    public void posContructor() {
        this.setRepository(realRepository);
    }


    public Optional<EmailType> UpdateById(long id, EmailType element) {
        Optional<EmailType> optionalCurrentCompany = this.repository.findById(id);

        this.businessValidationsRules(Optional.of(element), optionalCurrentCompany, Optional.empty(), Constans.CrudOperations.UPDATE);

        if (optionalCurrentCompany.isPresent()) {
            EmailType currentProduct = optionalCurrentCompany.get();

            if (element.getName() != null) {
                currentProduct.setName(element.getName());
            }


            return Optional.of((EmailType) this.repository.save(currentProduct));
        }
        return Optional.empty();

    }


    @Override
    protected void businessValidationsRules(Optional<EmailType> onDbElement, Optional<EmailType> goalToUpdate, Optional<Long> id, Constans.CrudOperations crudOperations) {
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
