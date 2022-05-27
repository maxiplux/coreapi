package io.base.coreapi.services.generics.impl;


import io.base.coreapi.model.EmailDataSource;
import io.base.coreapi.repositories.EmailDataSourceRepository;
import io.base.coreapi.services.generics.EmailDataSourceServices;
import io.base.coreapi.services.generics.cruds.CrudServicesImpl;
import io.base.coreapi.utils.Constans;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@Slf4j
public class EmailDataSourceServicesImpl extends CrudServicesImpl<EmailDataSource> implements EmailDataSourceServices<EmailDataSource> {

    @Autowired
    private EmailDataSourceRepository realRepository;


    @PostConstruct
    public void postContructor() {
        this.setRepository(realRepository);
    }


    public Optional<EmailDataSource> UpdateById(long id, EmailDataSource element) {
        Optional<EmailDataSource> optionalCurrentCompany = this.repository.findById(id);

        this.businessValidationsRules(Optional.of(element), optionalCurrentCompany, Optional.empty(), Constans.CrudOperations.UPDATE);

        if (optionalCurrentCompany.isPresent()) {
            EmailDataSource currentProduct = optionalCurrentCompany.get();

            if (element.getName() != null) {
                currentProduct.setName(element.getName());
            }


            return Optional.of((EmailDataSource) this.repository.save(currentProduct));
        }
        return Optional.empty();

    }

    @Override
    protected void businessValidationsRules(Optional<EmailDataSource> onDbElement, Optional<EmailDataSource> goalToUpdate, Optional<Long> id, Constans.CrudOperations crudOperations) {
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
