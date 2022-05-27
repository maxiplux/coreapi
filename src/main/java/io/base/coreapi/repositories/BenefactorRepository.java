package io.base.coreapi.repositories;

import io.base.coreapi.model.Benefactor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BenefactorRepository extends PagingAndSortingRepository<Benefactor, Long> {


}

