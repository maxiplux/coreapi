package io.base.coreapi.repositories;

import io.base.coreapi.model.Genero;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends PagingAndSortingRepository<Genero, Long> {

}

