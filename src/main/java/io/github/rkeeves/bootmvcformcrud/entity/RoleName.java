package io.github.rkeeves.bootmvcformcrud.entity;

import lombok.Getter;

public enum RoleName {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    @Getter
    private final String dataBaseValue;

    RoleName(String dataBaseValue) {
        this.dataBaseValue = dataBaseValue;
    }
}
