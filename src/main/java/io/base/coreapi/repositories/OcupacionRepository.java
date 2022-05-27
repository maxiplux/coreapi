package io.base.coreapi.repositories;


import io.base.coreapi.model.Ocupacion;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OcupacionRepository extends PagingAndSortingRepository<Ocupacion, Long> {


}

