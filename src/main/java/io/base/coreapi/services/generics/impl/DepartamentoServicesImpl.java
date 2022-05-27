package io.base.coreapi.services.generics.impl;


import io.base.coreapi.model.Departamento;
import io.base.coreapi.repositories.DepartamentoRepository;
import io.base.coreapi.services.generics.DepartamentoServices;
import io.base.coreapi.services.generics.cruds.CrudServicesImpl;
import io.base.coreapi.utils.Constans;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@Slf4j
public class DepartamentoServicesImpl extends CrudServicesImpl<Departamento> implements DepartamentoServices<Departamento> {

    @Autowired
    private DepartamentoRepository realRepository;


    @PostConstruct
    public void postContructor() {
        this.setRepository(realRepository);
    }


    public Optional<Departamento> UpdateById(long id, Departamento element) {
        Optional<Departamento> optionalCurrentCompany = this.repository.findById(id);

        this.businessValidationsRules(Optional.of(element), optionalCurrentCompany, Optional.empty(), Constans.CrudOperations.UPDATE);

        if (optionalCurrentCompany.isPresent()) {
            Departamento currentProduct = optionalCurrentCompany.get();

            if (element.getNombre() != null) {
                currentProduct.setNombre(element.getNombre());
            }


            return Optional.of((Departamento) this.repository.save(currentProduct));
        }
        return Optional.empty();

    }

    @Override
    protected void businessValidationsRules(Optional<Departamento> onDbElement, Optional<Departamento> goalToUpdate, Optional<Long> id, Constans.CrudOperations crudOperations) {
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
