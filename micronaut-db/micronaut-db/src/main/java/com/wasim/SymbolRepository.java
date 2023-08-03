package com.wasim;

import com.wasim.persistence.jpa.SymbolEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface SymbolRepository extends CrudRepository<SymbolEntity,String> {

    @Override
    List<SymbolEntity> findAll();
}
