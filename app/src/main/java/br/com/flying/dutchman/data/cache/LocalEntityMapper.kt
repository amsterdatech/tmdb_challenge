package br.com.flying.dutchman.data.cache

interface LocalEntityMapper<M, E> {
    fun mapFrom(type: M): E

    fun mapTo(type: E): M
}

