package io.base.coreapi.repositories;

import io.base.coreapi.model.Cuota;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuotaRepository extends PagingAndSortingRepository<Cuota, Long> {

}

