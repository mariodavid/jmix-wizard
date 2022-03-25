package de.diedavids.jmix.jwexample.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDelete;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "PRODUCT")
@Entity
public class Product {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @InstanceName
    @NotNull
    @Column(name = "NAME", nullable = false)
    protected String name;

    @NotNull
    @Column(name = "CODE", nullable = false)
    protected String code;

    @NotNull
    @Column(name = "TAX", nullable = false)
    protected Integer tax;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "product")
    protected List<ProductPrice> prices;

    public void setPrices(List<ProductPrice> prices) {
        this.prices = prices;
    }

    public List<ProductPrice> getPrices() {
        return prices;
    }


    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public Integer getTax() {
        return tax;
    }


    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}