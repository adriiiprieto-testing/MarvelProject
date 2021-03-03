package es.adriiiprieto.marvelproject.di

import android.content.Context
import es.adriiiprieto.marvelproject.base.util.NetworkManager
import es.adriiiprieto.marvelproject.data.config.MarvelRetrofit
import es.adriiiprieto.marvelproject.data.marvel.repository.MarvelRepositoryImpl
import es.adriiiprieto.marvelproject.data.marvel.repository.network.MarvelNetwork
import es.adriiiprieto.marvelproject.data.marvel.repository.network.MarvelService
import es.adriiiprieto.marvelproject.domain.repository.MarvelRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {

    factory {
        provideMarvelRepository(get())
    }

    single {
        provideRetrofit(androidContext())
    }

}

fun provideRetrofit(context: Context): Retrofit {
    return MarvelRetrofit(NetworkManager(context)).loadRetrofit()
}

fun provideMarvelRepository(retrofit: Retrofit): MarvelRepository {
    return MarvelRepositoryImpl(MarvelNetwork(retrofit.create(MarvelService::class.java)))
}