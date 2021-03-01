package es.adriiiprieto.marvelproject.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.adriiiprieto.marvelproject.data.config.MarvelRetrofit
import es.adriiiprieto.marvelproject.data.marvel.repository.MarvelRepositoryImpl
import es.adriiiprieto.marvelproject.data.marvel.repository.network.MarvelNetwork
import es.adriiiprieto.marvelproject.data.marvel.repository.network.MarvelService
import es.adriiiprieto.marvelproject.domain.repository.MarvelRepository


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

//    @Provides
//    fun provideContext(@ApplicationContext context: Context): Context = context
//
//    @Provides
//    fun networkManager(context: Context): NetworkManager = NetworkManager(context)
//
//    @Provides
//    fun providesMarvelRetrofit(networkManager: NetworkManager): MarvelRetrofit = MarvelRetrofit(networkManager)
//
//    @Provides
//    fun providesRetrofit(marvelRetrofit: MarvelRetrofit): Retrofit = marvelRetrofit.loadRetrofit()
//
//    @Provides
//    fun provideMarvelService(retrofit: Retrofit): MarvelService = retrofit.create(MarvelService::class.java)
//
//    @Provides
//    fun provideMarvelNetwork(service: MarvelService): MarvelNetwork = MarvelNetwork(service)
//
//    @Provides
//    fun provideMarvelRepositoryImpl(marvelNetwork: MarvelNetwork): MarvelRepositoryImpl = MarvelRepositoryImpl(marvelNetwork)
//
//    @Provides
//    fun provideMarvelRepository(repositoryImpl: MarvelRepositoryImpl): MarvelRepository = repositoryImpl


    @Provides
    fun provideMarvelRepository(retrofit: MarvelRetrofit): MarvelRepository = MarvelRepositoryImpl(MarvelNetwork(retrofit.loadRetrofit().create(MarvelService::class.java)))


}