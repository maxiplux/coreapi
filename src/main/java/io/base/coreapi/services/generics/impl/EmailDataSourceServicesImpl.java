package io.base.coreapi.services.generics.impl;


import io.base.coreapi.model.EmailDataSource;
import io.base.coreapi.repositories.EmailDataSourceRepository;
import io.base.coreapi.services.generics.EmailDataSourceServices;
import io.base.coreapi.services.generics.cruds.CrudServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class EmailDataSourceServicesImpl extends CrudServicesImpl<EmailDataSource> implements EmailDataSourceServices<EmailDataSource> {

    @Autowired
    private EmailDataSourceRepository realRepository;


    @PostConstruct
    public void posContructor() {
        this.setRepository(realRepository);
    }


    public Optional<EmailDataSource> UpdateById(long id, EmailDataSource element) {
        Optional<EmailDataSource> optionalCurrentCompany = this.repository.findById(id);
        if (optionalCurrentCompany.isPresent()) {
            EmailDataSource currentProduct = optionalCurrentCompany.get();

            if (element.getName() != null) {
                currentProduct.setName(element.getName());
            }


            return Optional.of((EmailDataSource) this.repository.save(currentProduct));
        }
        return Optional.empty();

    }


}
