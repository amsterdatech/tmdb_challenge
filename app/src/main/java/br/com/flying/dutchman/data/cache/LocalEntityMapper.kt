package br.com.flying.dutchman.data.cache

interface LocalEntityMapper<in M, out E> {
    fun mapFrom(type: M): E
}