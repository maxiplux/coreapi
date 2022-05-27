package io.base.coreapi.services.generics.impl;


import io.base.coreapi.model.Benefactor;
import io.base.coreapi.repositories.BenefactorRepository;
import io.base.coreapi.services.generics.BenefactorServices;
import io.base.coreapi.services.generics.cruds.CrudServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class BenefactorServicesImpl extends CrudServicesImpl<Benefactor> implements BenefactorServices<Benefactor> {

    @Autowired
    private BenefactorRepository realRepository;


    @PostConstruct
    public void posContructor() {
        this.setRepository(realRepository);
    }


    public Optional<Benefactor> UpdateById(long id, Benefactor element) {
        Optional<Benefactor> optionalCurrentCompany = this.repository.findById(id);
        if (optionalCurrentCompany.isPresent()) {
            Benefactor currentProduct = optionalCurrentCompany.get();

            /*if (element.getNombre() != null) {
                currentProduct.setNombre(element.getNombre());
            }*/


            return Optional.of((Benefactor) this.repository.save(currentProduct));
        }
        return Optional.empty();

    }


}
