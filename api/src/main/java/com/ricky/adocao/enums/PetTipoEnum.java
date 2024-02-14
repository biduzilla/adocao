package com.ricky.adocao.enums;

public enum PetTipoEnum {
    NONE,
    PERDIDO,
    ACHADO,
    ADOCAO;

    public String getDecricao() {
        return switch (this) {
            case NONE -> "";
            case PERDIDO -> "PET_TIPO_PERDIDO";
            case ACHADO -> "PET_TIPO_PERDIDO";
            case ADOCAO -> "PET_TIPO_ADOCAO";
            default -> throw new IllegalArgumentException("Unexpected value: " + this);
        };
    }

    @Override
    public String toString() {
        return getDecricao().replace("PET_TIPO_", "");
    }
}

