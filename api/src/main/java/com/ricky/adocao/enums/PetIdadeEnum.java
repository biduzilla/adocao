package com.ricky.adocao.enums;

public enum PetIdadeEnum {
    NONE,
    FILHOTE,
    JOVEM,
    ADULTO,
    ID0SO;

    public String getDecricao(){
        return switch (this) {
            case NONE -> "";
            case FILHOTE -> "PET_IDADE_FILHOTE";
            case JOVEM -> "PET_IDADE_JOVEM";
            case ADULTO -> "PET_IDADE_ADULTO";
            case ID0SO -> "PET_IDADE_ID0SO";
            default -> throw new IllegalArgumentException("Unexpected value: " + this);
        };
    }

    @Override
    public String toString() {
        return getDecricao().replace("PET_IDADE_", "");
    }
}
