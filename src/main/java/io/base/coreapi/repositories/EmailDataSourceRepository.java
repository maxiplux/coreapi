package io.base.coreapi.repositories;


import io.base.coreapi.model.EmailDataSource;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailDataSourceRepository extends PagingAndSortingRepository<EmailDataSource, Long> {

}
