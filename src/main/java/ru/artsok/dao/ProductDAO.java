package ru.artsok.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.artsok.entity.ProductEntity;

@Repository
public interface ProductDAO extends CrudRepository<ProductEntity, Long> {
}
