package es.adriiiprieto.marvelproject.data.network

import es.adriiiprieto.marvelproject.data.model.ResponseAllCharactersDataModel
import retrofit2.http.GET

interface MarvelService {

    @GET("v1/public/characters")
    suspend fun getAllCharacters(): ResponseAllCharactersDataModel

}