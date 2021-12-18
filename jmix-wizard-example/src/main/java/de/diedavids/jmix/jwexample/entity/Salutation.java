package de.diedavids.jmix.jwexample.entity;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum Salutation implements EnumClass<String> {

    MR("MR"),
    MRS("MRS");

    private String id;

    Salutation(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static Salutation fromId(String id) {
        for (Salutation at : Salutation.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}