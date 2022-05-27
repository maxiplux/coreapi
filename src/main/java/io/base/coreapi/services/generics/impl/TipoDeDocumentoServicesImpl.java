package io.base.coreapi.services.generics.impl;


import io.base.coreapi.model.TipoDocumento;
import io.base.coreapi.repositories.TipoDocumentoRepository;
import io.base.coreapi.services.generics.TipoDeDocumentoServices;
import io.base.coreapi.services.generics.cruds.CrudServicesImpl;
import io.base.coreapi.utils.Constans;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@Slf4j
public class TipoDeDocumentoServicesImpl extends CrudServicesImpl<TipoDocumento> implements TipoDeDocumentoServices<TipoDocumento> {

    @Autowired
    private TipoDocumentoRepository realRepository;


    @PostConstruct
    public void posContructor() {
        this.setRepository(realRepository);
    }


    public Optional<TipoDocumento> UpdateById(long id, TipoDocumento element) {

        Optional<TipoDocumento> optionalCurrentCompany = this.repository.findById(id);

        this.businessValidationsRules(Optional.of(element), optionalCurrentCompany, Optional.empty(), Constans.CrudOperations.UPDATE);

        if (optionalCurrentCompany.isPresent()) {
            TipoDocumento currentProduct = optionalCurrentCompany.get();

            if (element.getNombre() != null) {
                currentProduct.setNombre(element.getNombre());
            }


            return Optional.of((TipoDocumento) this.repository.save(currentProduct));
        }
        return Optional.empty();

    }

    @Override
    protected void businessValidationsRules(Optional<TipoDocumento> onDbElement, Optional<TipoDocumento> goalToUpdate, Optional<Long> id, Constans.CrudOperations crudOperations) {
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

