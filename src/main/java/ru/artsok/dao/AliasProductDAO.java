package ru.artsok.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.artsok.entity.AliasEntity;
import ru.artsok.entity.AliasProductEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AliasProductDAO {

    private final EntityManager entityManager;

    @Autowired
    public AliasProductDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<AliasEntity> getAliasInProduct(Long idProduct){
        String sql = "Select e from " + AliasProductEntity.class.getName() + " e " + " Where e.id = :id ";
        Query query = this.entityManager.createQuery(sql, AliasProductEntity.class);
        query.setParameter("id", idProduct);
        List<AliasProductEntity> aliasProductEntities =  query.getResultList();
        List<AliasEntity> aliasEntities = new ArrayList<>();
        return aliasEntities;
    }
}
