package de.diedavids.jmix.jwexample.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDelete;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "ORDER_")
@Entity(name = "Order_")
public class Order {
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
    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(name = "ORDER_DATE", nullable = false)
    protected Date orderDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CUSTOMER_ID")
    protected Customer customer;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DELIVERY_ADDRESS_ID")
    protected Address deliveryAddress;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "INVOICE_ADDRESS_ID")
    protected Address invoiceAddress;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "order")
    protected List<OrderLine> orderLines;

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }


    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setInvoiceAddress(Address invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    public Address getInvoiceAddress() {
        return invoiceAddress;
    }
}