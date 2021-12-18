package de.diedavids.jmix.jwexample.entity;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum Unit implements EnumClass<String> {

    PIECE("PIECE"),
    LITER("LITER"),
    METER("METER");

    private String id;

    Unit(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static Unit fromId(String id) {
        for (Unit at : Unit.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}