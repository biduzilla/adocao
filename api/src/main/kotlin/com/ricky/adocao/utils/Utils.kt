package com.ricky.adocao.utils

import org.springframework.data.domain.Sort


fun orderByToSort(orderBy: String): Sort {
    var sort: Sort? = null

    if (isNullOrEmpty(orderBy)) {
        sort = Sort.by(Sort.Direction.DESC, "dataPublicacao")
    } else {
        val aux = orderBy.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val field = aux[0]
        val direction = if (aux.size > 1) aux[1] else "ASC"

        sort = Sort.by(Sort.Direction.fromString(direction), field)
    }

    return sort
}

fun isNullOrEmpty(value: String?): Boolean {
    return value == null || value.trim().isEmpty()
}
