package com.ricky.adocao.enums;

public enum PetGeneroEnum {
    NONE,
    MACHO,
    FEMEA;

    public String getDecricao() {
        return switch (this) {
            case NONE -> "";
            case MACHO -> "PET_GENERO_MACHO";
            case FEMEA -> "PET_GENERO_FEMEA";
            default -> throw new IllegalArgumentException("Unexpected value: " + this);
        };
    }

    @Override
    public String toString() {
        return getDecricao().replace("PET_GENERO_", "");
    }
}

