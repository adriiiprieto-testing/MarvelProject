package es.adriiiprieto.marvelproject.domain.repository

import es.adriiiprieto.marvelproject.data.marvel.model.Character
import es.adriiiprieto.marvelproject.data.marvel.model.Comic

interface MarvelRepository {
    suspend fun getAllCharacters(limit: Int): List<Character>
    suspend fun getCharacter(characterId: Int): Character
    suspend fun getComic(comicId: Int): Comic
}