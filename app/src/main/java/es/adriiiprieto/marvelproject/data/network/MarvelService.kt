package es.adriiiprieto.marvelproject.data.network

import es.adriiiprieto.marvelproject.data.model.ResponseAllCharactersDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

    @GET("v1/public/characters")
    suspend fun getAllCharacters(@Query("limit") limit: Int): ResponseAllCharactersDataModel

}