package io.base.coreapi.repositories;

import io.base.coreapi.model.Grupo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends PagingAndSortingRepository<Grupo, Long> {

}

