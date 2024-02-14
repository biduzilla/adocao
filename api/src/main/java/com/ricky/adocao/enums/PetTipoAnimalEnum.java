package com.ricky.adocao.enums;

public enum PetTipoAnimalEnum {
    NONE,
    GATO,
    CACHORRO;

    public String getDecricao() {
        return switch (this) {
            case NONE -> "";
            case GATO -> "PET_TIPO_ANIMAL_GATO";
            case CACHORRO -> "PET_TIPO_ANIMAL_CACHORRO";
            default -> throw new IllegalArgumentException("Unexpected value: " + this);
        };
    }

    @Override
    public String toString() {
        return getDecricao().replace("PET_TIPO_", "");
    }
}

