package com.ricky.adocao.mapper

interface Mapper<T, U> {
    fun map(t: T): U
}