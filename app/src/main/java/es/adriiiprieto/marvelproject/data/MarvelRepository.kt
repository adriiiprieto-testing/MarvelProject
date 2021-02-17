package es.adriiiprieto.marvelproject.data

import es.adriiiprieto.marvelproject.data.model.Character
import es.adriiiprieto.marvelproject.data.network.MarvelNetwork

class MarvelRepository {

    suspend fun getAllCharacters(limit: Int): List<Character> {
        return MarvelNetwork().getAllCharacters(limit).data.results
    }

}