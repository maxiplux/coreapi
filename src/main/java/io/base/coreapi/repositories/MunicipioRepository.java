package io.base.coreapi.repositories;


import io.base.coreapi.model.Municipio;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipioRepository extends PagingAndSortingRepository<Municipio, Long> {

}

