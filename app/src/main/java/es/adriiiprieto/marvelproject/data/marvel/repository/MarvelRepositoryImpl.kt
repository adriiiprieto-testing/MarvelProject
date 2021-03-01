package es.adriiiprieto.marvelproject.data.marvel.repository

import android.util.Log
import es.adriiiprieto.marvelproject.data.marvel.model.Character
import es.adriiiprieto.marvelproject.data.marvel.model.Comic
import es.adriiiprieto.marvelproject.data.marvel.repository.network.MarvelNetwork
import es.adriiiprieto.marvelproject.domain.repository.MarvelRepository
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(private val network: MarvelNetwork) : MarvelRepository {

    override suspend fun getAllCharacters(limit: Int): List<Character> {
        return network.getAllCharacters(limit).data.results
    }

    override suspend fun getCharacter(characterId: Int): Character {
        val response = network.getCharacter(characterId)
        val response2 = response.data.results
        return if (response2.isNotEmpty()) response2[0] else throw NoCharacterException()
    }

    override suspend fun getComic(comicId: Int): Comic {
        return network.getComic(comicId).data.results.first()
    }

}

class NoCharacterException : Exception()