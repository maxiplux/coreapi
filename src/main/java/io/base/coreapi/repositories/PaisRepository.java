package io.base.coreapi.repositories;

import io.base.coreapi.model.Pais;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends PagingAndSortingRepository<Pais, Long> {



}

