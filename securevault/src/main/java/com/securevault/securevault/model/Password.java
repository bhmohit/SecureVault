package com.securevault.securevault.model;

public record Password(
        int id,
        String software,
        String username,
        String password,
        byte[] iv
) {
}
