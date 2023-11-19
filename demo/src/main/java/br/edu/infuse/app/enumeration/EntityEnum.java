package br.edu.infuse.app.enumeration;

import lombok.Getter;
@Getter
public enum EntityEnum {
    ENTITY("entity"),
    ORDER("order"),
    CLIENT("client");

    private String id;

    EntityEnum(String id) {
        this.id = id;
    }
}