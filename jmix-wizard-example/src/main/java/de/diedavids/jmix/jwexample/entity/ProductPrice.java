package de.diedavids.jmix.jwexample.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@JmixEntity
@Table(name = "PRODUCT_PRICE")
@Entity
public class ProductPrice {
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

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCT_ID")
    protected Product product;

    @InstanceName
    @NotNull
    @Column(name = "VALUE_", nullable = false)
    protected BigDecimal value;

    @NotNull
    @Column(name = "UNIT", nullable = false)
    protected String unit;

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setUnit(Unit unit) {
        this.unit = unit == null ? null : unit.getId();
    }

    public Unit getUnit() {
        return unit == null ? null : Unit.fromId(unit);
    }

}