package io.base.coreapi.services.generics.impl;


import io.base.coreapi.model.Departamento;
import io.base.coreapi.repositories.DepartamentoRepository;
import io.base.coreapi.services.generics.DepartamentoServices;
import io.base.coreapi.services.generics.cruds.CrudServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class DepartamentoServicesImpl extends CrudServicesImpl<Departamento> implements DepartamentoServices<Departamento> {

    @Autowired
    private DepartamentoRepository realRepository;


    @PostConstruct
    public void posContructor() {
        this.setRepository(realRepository);
    }


    public Optional<Departamento> UpdateById(long id, Departamento element) {
        Optional<Departamento> optionalCurrentCompany = this.repository.findById(id);
        if (optionalCurrentCompany.isPresent()) {
            Departamento currentProduct = optionalCurrentCompany.get();

            if (element.getNombre() != null) {
                currentProduct.setNombre(element.getNombre());
            }


            return Optional.of((Departamento) this.repository.save(currentProduct));
        }
        return Optional.empty();

    }


}
