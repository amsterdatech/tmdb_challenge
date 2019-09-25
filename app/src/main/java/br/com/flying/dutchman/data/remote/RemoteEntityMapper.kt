package br.com.flying.dutchman.data.remote

interface RemoteEntityMapper<in M, out E> {
    fun mapFromRemote(type: M): E
}
