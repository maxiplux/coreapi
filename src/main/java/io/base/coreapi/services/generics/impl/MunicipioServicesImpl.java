package io.base.coreapi.services.generics.impl;


import io.base.coreapi.model.Municipio;
import io.base.coreapi.repositories.MunicipioRepository;
import io.base.coreapi.services.generics.MunicipioServices;
import io.base.coreapi.services.generics.cruds.CrudServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class MunicipioServicesImpl extends CrudServicesImpl<Municipio> implements MunicipioServices<Municipio> {

    @Autowired
    private MunicipioRepository realRepository;


    @PostConstruct
    public void posContructor() {
        this.setRepository(realRepository);
    }


    public Optional<Municipio> UpdateById(long id, Municipio element) {
        Optional<Municipio> optionalCurrentCompany = this.repository.findById(id);
        if (optionalCurrentCompany.isPresent()) {
            Municipio currentProduct = optionalCurrentCompany.get();

            if (element.getNombre() != null) {
                currentProduct.setNombre(element.getNombre());
            }


            return Optional.of((Municipio) this.repository.save(currentProduct));
        }
        return Optional.empty();

    }


}
