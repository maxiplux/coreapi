package io.base.coreapi.repositories;


import io.base.coreapi.model.BatchEmail;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchEmailRepository extends PagingAndSortingRepository<BatchEmail, Long> {

}
