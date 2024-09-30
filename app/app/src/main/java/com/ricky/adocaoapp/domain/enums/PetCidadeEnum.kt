package com.ricky.adocao.enums

enum class PetCidadeEnum {
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

    override fun toString(): String {
        return when (this) {
            CEILANDIA -> "Ceilância"
            SAMAMBAIA -> "Samambaia"
            PLANO_PILOTO -> "Plano Piloto"
            TAGUATINGA -> "Taguatinga"
            PLANALTINA -> "PLanaltina"
            GUARA_1 -> "Guará 1"
            GUARA_2 -> "Guará 1"
            GAMA -> "Gama"
            RECANTO_DAS_EMAS -> "Recanto das Emas"
            SANTA_MARIA -> "Santa Maria"
            AGUAS_CLARAS -> "Águas Claras"
            SAO_SEBASTIAO -> "São Sebastião"
            RIACHO_FUNTO_2 -> "Riacho Fundo 2"
            SOL_NASCENTE -> "Sol Nascente"
            SOBRADINHO_2 -> "Sobradinho 2"
            SOBRADINHO -> "Sobradinho"
            VINCENTE_PIRES -> "Vicente Pires"
            PARANOA -> "Paranoá"
            ITAPOA -> "Itapoã"
            SUDOESTE -> "Sudoeste"
            OCTOGONAL -> "Octogonal"
            BRAZLANDIA -> "Brazlandia"
            JARDIM_BOTANICO -> "Jardim Botânico"
            RIACHO_FUNDO -> "Riacho Fundo"
            ARNIQUEIRA -> "Arniqueiras"
            LAGO_NORTE -> "Lago Norte"
            SCIA -> "SCIA"
            CRUZEIRO_NOVO -> "Cruzeiro Novo"
            CRUZEIRO_VELHO -> "Cruzeiro Velho"
            LAGO_SUL -> "Lago Sul"
            NOROESTE -> "Noroeste"
            NUCLEO_BANDEIRANTE -> "Núcleo Bandeirante"
            PARK_WAY -> "Park Way"
            CANDANGOLANDIA -> "Candangolândia"
            VARJAO -> "Varjão"
            FERCAL -> "Fercal"
            SIA -> "SIA"
        }
    }
}