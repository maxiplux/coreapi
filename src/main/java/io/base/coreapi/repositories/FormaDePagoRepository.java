package io.base.coreapi.repositories;

import io.base.coreapi.model.FormaDePago;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaDePagoRepository extends PagingAndSortingRepository<FormaDePago, Long> {

}

