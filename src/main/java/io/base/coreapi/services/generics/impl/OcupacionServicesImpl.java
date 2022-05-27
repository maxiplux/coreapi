package io.base.coreapi.services.generics.impl;


import io.base.coreapi.model.Ocupacion;
import io.base.coreapi.repositories.OcupacionRepository;
import io.base.coreapi.services.generics.OcupacionServices;
import io.base.coreapi.services.generics.cruds.CrudServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class OcupacionServicesImpl extends CrudServicesImpl<Ocupacion> implements OcupacionServices<Ocupacion> {

    @Autowired
    private OcupacionRepository realRepository;


    @PostConstruct
    public void posContructor() {
        this.setRepository(realRepository);
    }


    public Optional<Ocupacion> UpdateById(long id, Ocupacion element) {
        Optional<Ocupacion> optionalCurrentCompany = this.repository.findById(id);
        if (optionalCurrentCompany.isPresent()) {
            Ocupacion currentProduct = optionalCurrentCompany.get();

            if (element.getNombre() != null) {
                currentProduct.setNombre(element.getNombre());
            }


            return Optional.of((Ocupacion) this.repository.save(currentProduct));
        }
        return Optional.empty();

    }


}
