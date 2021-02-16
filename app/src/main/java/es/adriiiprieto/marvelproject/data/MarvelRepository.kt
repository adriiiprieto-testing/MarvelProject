package es.adriiiprieto.marvelproject.data

import es.adriiiprieto.marvelproject.data.model.ResponseAllCharactersDataModel
import es.adriiiprieto.marvelproject.data.network.MarvelNetwork

class MarvelRepository {

    suspend fun getAllCharacters(): ResponseAllCharactersDataModel{
        return MarvelNetwork().getAllCharacters()
    }
}