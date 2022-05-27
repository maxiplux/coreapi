package io.base.coreapi.services.generics.impl;


import io.base.coreapi.model.Genero;
import io.base.coreapi.repositories.GeneroRepository;
import io.base.coreapi.services.generics.GeneroServices;
import io.base.coreapi.services.generics.cruds.CrudServicesImpl;
import io.base.coreapi.utils.Constans;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@Slf4j
public class GeneroServicesImpl extends CrudServicesImpl<Genero> implements GeneroServices<Genero> {

    @Autowired
    private GeneroRepository realRepository;


    @PostConstruct
    public void posContructor() {
        this.setRepository(realRepository);
    }


    public Optional<Genero> UpdateById(long id, Genero element) {
        Optional<Genero> optionalCurrentCompany = this.repository.findById(id);

        this.businessValidationsRules(Optional.of(element), optionalCurrentCompany, Optional.empty(), Constans.CrudOperations.UPDATE);

        if (optionalCurrentCompany.isPresent()) {
            Genero currentProduct = optionalCurrentCompany.get();

            if (element.getNombre() != null) {
                currentProduct.setNombre(element.getNombre());
            }


            return Optional.of((Genero) this.repository.save(currentProduct));
        }
        return Optional.empty();

    }


    @Override
    protected void businessValidationsRules(Optional<Genero> onDbElement, Optional<Genero> goalToUpdate, Optional<Long> id, Constans.CrudOperations crudOperations) {
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
