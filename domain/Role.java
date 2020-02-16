package ru.kaiko.rehospital.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, REFERENCE, DOCTOR, ADMIN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}

