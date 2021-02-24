package es.adriiiprieto.marvelproject.data

import es.adriiiprieto.marvelproject.data.model.Character
import es.adriiiprieto.marvelproject.data.model.Comic
import es.adriiiprieto.marvelproject.data.model.ResponseGetComicDataModel
import es.adriiiprieto.marvelproject.data.network.MarvelNetwork

class MarvelRepository {

    suspend fun getAllCharacters(limit: Int): List<Character> {
        return MarvelNetwork().getAllCharacters(limit).data.results
    }

    suspend fun getCharacter(characterId: Int): Character {
        val response = MarvelNetwork().getCharacter(characterId)
        val response2 = response.data.results
        return if (response2.isNotEmpty()) response2[0] else throw NoCharacterException()
    }

    suspend fun getComic(comicId: Int): Comic {
        return MarvelNetwork().getComic(comicId).data.results.first()
    }
}

class NoCharacterException : Exception()