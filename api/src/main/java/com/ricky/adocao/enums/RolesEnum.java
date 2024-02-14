package com.ricky.adocao.enums;

public enum RolesEnum {
    NONE,
    BASIC,
    ADMIN;
    public String getDecricao(){
        return switch (this) {
            case NONE -> "";
            case BASIC -> "ROLE_BASIC";
            case ADMIN -> "ROLE_ADMIN";
            default -> throw new IllegalArgumentException("Unexpected value: " + this);
        };
    }

    @Override
    public String toString() {
        return getDecricao().replace("ROLE_", "");
    }
}
