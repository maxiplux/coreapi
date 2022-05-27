package io.base.coreapi.repositories;


import io.base.coreapi.model.EmailType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailTypeRepository extends PagingAndSortingRepository<EmailType, Long> {
    EmailType findByNameEquals(String name);


}
