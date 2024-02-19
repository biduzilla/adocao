package com.ricky.adocao.enums;

public enum PetCidadeEnum {
    NONE,
    CEILANDIA,
    SAMAMBAIA,
    PLANO_PILOTO,
    TAGUATINGA,
    PLANALTINA,
    GUARA_1,
    GUARA_2,
    GAMA,
    RECANTO_DAS_EMAS,
    SANTA_MARIA,
    AGUAS_CLARAS,
    SAO_SEBASTIAO,
    RIACHO_FUNTO_2,
    SOL_NASCENTE,
    SOBRADINHO_2,
    SOBRADINHO,
    VINCENTE_PIRES,
    PARANOA,
    ITAPOA,
    SUDOESTE,
    OCTOGONAL,
    BRAZLANDIA,
    JARDIM_BOTANICO,
    RIACHO_FUNDO,
    ARNIQUEIRA,
    LAGO_NORTE,
    SCIA,
    CRUZEIRO_NOVO,
    CRUZEIRO_VELHO,
    LAGO_SUL,
    NOROESTE,
    NUCLEO_BANDEIRANTE,
    PARK_WAY,
    CANDANGOLANDIA,
    VARJAO,
    FERCAL,
    SIA;

    public String getDecricao() {
        return switch (this) {
            case NONE -> "";
            case CEILANDIA -> "PET_CIDADE_CEILANDIA";
            case SAMAMBAIA -> "PET_CIDADE_SAMAMBAIA";
            case PLANO_PILOTO -> "PET_CIDADE_PLANO_PILOTO";
            case TAGUATINGA -> "PET_CIDADE_TAGUATINGA";
            case PLANALTINA -> "PET_CIDADE_PLANALTINA";
            case GUARA_1 -> "PET_CIDADE_GUARA_1";
            case GUARA_2 -> "PET_CIDADE_GUARA_2";
            case GAMA -> "PET_CIDADE_GAMA";
            case RECANTO_DAS_EMAS -> "PET_CIDADE_RECANTO_DAS_EMAS";
            case SANTA_MARIA -> "PET_CIDADE_SANTA_MARIA";
            case AGUAS_CLARAS -> "PET_CIDADE_AGUAS_CLARAS";
            case SAO_SEBASTIAO -> "PET_CIDADE_SAO_SEBASTIAO";
            case RIACHO_FUNTO_2 -> "PET_CIDADE_RIACHO_FUNTO_2";
            case SOL_NASCENTE -> "PET_CIDADE_SOL_NASCENTE";
            case SOBRADINHO_2 -> "PET_CIDADE_SOBRADINHO_2";
            case SOBRADINHO -> "PET_CIDADE_SOBRADINHO";
            case VINCENTE_PIRES -> "PET_CIDADE_VINCENTE_PIRES";
            case PARANOA -> "PET_CIDADE_PARANOA";
            case ITAPOA -> "PET_CIDADE_ITAPOA";
            case SUDOESTE -> "PET_CIDADE_SUDOESTE";
            case OCTOGONAL -> "PET_CIDADE_OCTOGONAL";
            case BRAZLANDIA -> "PET_CIDADE_BRAZLANDIA";
            case JARDIM_BOTANICO -> "PET_CIDADE_JARDIM_BOTANICO";
            case RIACHO_FUNDO -> "PET_CIDADE_RIACHO_FUNDO";
            case ARNIQUEIRA -> "PET_CIDADE_ARNIQUEIRA";
            case LAGO_NORTE -> "PET_CIDADE_LAGO_NORTE";
            case SCIA -> "PET_CIDADE_SCIA";
            case CRUZEIRO_NOVO -> "PET_CIDADE_CRUZEIRO_NOVO";
            case CRUZEIRO_VELHO -> "PET_CIDADE_CRUZEIRO_VELHO";
            case LAGO_SUL -> "PET_CIDADE_LAGO_SUL";
            case NOROESTE -> "PET_CIDADE_NOROESTE";
            case NUCLEO_BANDEIRANTE -> "PET_CIDADE_NUCLEO_BANDEIRANTE";
            case PARK_WAY -> "PET_CIDADE_PARK_WAY";
            case CANDANGOLANDIA -> "PET_CIDADE_CANDANGOLANDIA";
            case VARJAO -> "PET_CIDADE_VARJAO";
            case FERCAL -> "PET_CIDADE_FERCAL";
            case SIA -> "PET_CIDADE_SIA";

            default -> throw new IllegalArgumentException("Unexpected value: " + this);
        };
    }

    @Override
    public String toString() {
        return getDecricao().replace("PET_GENERO_", "");
    }
}
