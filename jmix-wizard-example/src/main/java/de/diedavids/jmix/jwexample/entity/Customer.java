package de.diedavids.jmix.jwexample.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDelete;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "CUSTOMER")
@Entity
public class Customer {
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

    @Column(name = "SALUTATION")
    protected String salutation;

    @NotNull
    @Column(name = "NAME", nullable = false)
    protected String name;

    @NotNull
    @Column(name = "FIRST_NAME", nullable = false)
    protected String firstName;

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTHDAY")
    protected Date birthday;

    @NotNull
    @Column(name = "EMAIL", nullable = false)
    protected String email;

    @Column(name = "PHONE")
    protected String phone;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "customer")
    protected List<Address> addresses;

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getBirthday() {
        return birthday;
    }


    public void setSalutation(Salutation salutation) {
        this.salutation = salutation == null ? null : salutation.getId();
    }

    public Salutation getSalutation() {
        return salutation == null ? null : Salutation.fromId(salutation);
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }


    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Address> getAddresses() {
        return addresses;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}