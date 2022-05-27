package io.base.coreapi.services.generics.impl;


import io.base.coreapi.model.Grupo;
import io.base.coreapi.repositories.GrupoRepository;
import io.base.coreapi.services.generics.GrupoServices;
import io.base.coreapi.services.generics.cruds.CrudServicesImpl;
import io.base.coreapi.utils.Constans;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@Slf4j
public class GrupoServicesImpl extends CrudServicesImpl<Grupo> implements GrupoServices<Grupo> {

    @Autowired
    private GrupoRepository realRepository;


    @PostConstruct
    public void posContructor() {
        this.setRepository(realRepository);
    }


    public Optional<Grupo> UpdateById(long id, Grupo element) {
        Optional<Grupo> optionalCurrentCompany = this.repository.findById(id);

        this.businessValidationsRules(Optional.of(element), optionalCurrentCompany, Optional.empty(), Constans.CrudOperations.UPDATE);


        if (optionalCurrentCompany.isPresent()) {
            Grupo currentProduct = optionalCurrentCompany.get();

            if (element.getNombre() != null) {
                currentProduct.setNombre(element.getNombre());
            }


            return Optional.of((Grupo) this.repository.save(currentProduct));
        }
        return Optional.empty();

    }

    @Override
    protected void businessValidationsRules(Optional<Grupo> onDbElement, Optional<Grupo> goalToUpdate, Optional<Long> id, Constans.CrudOperations crudOperations) {
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
