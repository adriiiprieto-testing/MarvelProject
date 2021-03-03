package es.adriiiprieto.marvelproject.data.marvel.repository.network

import es.adriiiprieto.marvelproject.data.marvel.model.ResponseAllCharactersDataModel
import es.adriiiprieto.marvelproject.data.marvel.model.ResponseCharacterDataModel
import es.adriiiprieto.marvelproject.data.marvel.model.ResponseGetComicDataModel

class MarvelNetwork(private val service: MarvelService) {

    suspend fun getAllCharacters(limit: Int): ResponseAllCharactersDataModel {
        return service.getAllCharacters(limit)
    }

    suspend fun getCharacter(characterId: Int): ResponseCharacterDataModel {
        return service.getCharacter(characterId)
    }

    suspend fun getComic(comicId: Int): ResponseGetComicDataModel {
        return service.getComic(comicId)
    }

}