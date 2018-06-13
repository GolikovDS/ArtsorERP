package ru.artsok.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.artsok.entity.AliasEntity;

@Repository
public interface AliasCrudDAO extends CrudRepository<AliasEntity, Long>{
}
