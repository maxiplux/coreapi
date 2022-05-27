package io.base.coreapi.services.generics.impl;


import io.base.coreapi.model.Ocupacion;
import io.base.coreapi.repositories.OcupacionRepository;
import io.base.coreapi.services.generics.OcupacionServices;
import io.base.coreapi.services.generics.cruds.CrudServicesImpl;
import io.base.coreapi.utils.Constans;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@Slf4j
public class OcupacionServicesImpl extends CrudServicesImpl<Ocupacion> implements OcupacionServices<Ocupacion> {

    @Autowired
    private OcupacionRepository realRepository;


    @PostConstruct
    public void posContructor() {
        this.setRepository(realRepository);
    }


    public Optional<Ocupacion> UpdateById(long id, Ocupacion element) {
        Optional<Ocupacion> optionalCurrentCompany = this.repository.findById(id);

        this.businessValidationsRules(Optional.of(element), optionalCurrentCompany, Optional.empty(), Constans.CrudOperations.UPDATE);

        if (optionalCurrentCompany.isPresent()) {
            Ocupacion currentProduct = optionalCurrentCompany.get();

            if (element.getNombre() != null) {
                currentProduct.setNombre(element.getNombre());
            }


            return Optional.of((Ocupacion) this.repository.save(currentProduct));
        }
        return Optional.empty();

    }


    @Override
    protected void businessValidationsRules(Optional<Ocupacion> onDbElement, Optional<Ocupacion> goalToUpdate, Optional<Long> id, Constans.CrudOperations crudOperations) {
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
