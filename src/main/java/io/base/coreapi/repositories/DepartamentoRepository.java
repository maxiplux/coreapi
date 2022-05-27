package io.base.coreapi.repositories;

import io.base.coreapi.model.Departamento;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends PagingAndSortingRepository<Departamento, Long> {

}

