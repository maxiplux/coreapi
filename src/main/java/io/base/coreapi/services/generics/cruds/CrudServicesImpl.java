package io.base.coreapi.services.generics.cruds;

import io.base.coreapi.utils.Constans;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

@Data
@Slf4j
public abstract class CrudServicesImpl<T> implements CrudServices<T> {


    protected PagingAndSortingRepository repository;


    public CrudServicesImpl(PagingAndSortingRepository repository) {
        this.repository = repository;
    }

    public CrudServicesImpl() {

    }


    @Override
    public Page<T> findAll(Pageable pageable) {

        return this.repository.findAll(pageable);
    }

    @Override
    public T create(T elememnt) {
        this.businessValidationsRules(Optional.of(elememnt),Optional.empty(),Optional.empty(),Constans.CrudOperations.CREATE);
        return (T) this.repository.save(elememnt);
    }


    @Override
    public abstract Optional<T> UpdateById(long id, T element);


    @Override
    public Boolean deleteById(long id) {
        this.businessValidationsRules(Optional.empty(),Optional.empty(),Optional.of(id),Constans.CrudOperations.DELETE);
        this.repository.deleteById(id);
        return this.repository.findById(id).isPresent();
    }

    @Override
    public Optional<T> findById(long id) {
        return this.repository.findById(id);
    }
    protected  abstract  void businessValidationsRules(Optional<T> onDbElement, Optional<T> goalToUpdate, Optional<Long> id, Constans.CrudOperations crudOperations);
}
