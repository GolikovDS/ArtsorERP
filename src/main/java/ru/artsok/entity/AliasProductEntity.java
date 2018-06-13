package ru.artsok.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Aliase_Product")
public class AliasProductEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToMany
    private List<AliasEntity> alias;

    @ManyToMany
    private List <ProductEntity> product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<AliasEntity> getAlias() {
        return alias;
    }

    public void setAlias(List<AliasEntity> alias) {
        this.alias = alias;
    }

    public List<ProductEntity> getProduct() {
        return product;
    }

    public void setProduct(List<ProductEntity> product) {
        this.product = product;
    }
}
