package com.alfonsovidrio.forohub.domain.user;

public record UserAuthenticationData(
        String name,
        String password
) {
}