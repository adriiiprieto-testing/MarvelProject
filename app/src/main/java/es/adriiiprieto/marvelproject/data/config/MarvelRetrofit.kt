package es.adriiiprieto.marvelproject.data.config

import es.adriiiprieto.marvelproject.BuildConfig
import es.adriiiprieto.marvelproject.base.exception.NoInternetException
import es.adriiiprieto.marvelproject.base.util.NetworkManager
import es.adriiiprieto.marvelproject.base.util.toMD5
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MarvelRetrofit(private val networkManager: NetworkManager) {

    fun loadRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(createHttpClient())
            .build()
    }

    private fun createHttpClient(): OkHttpClient {
        // Create OkHttpClient
        val builder = OkHttpClient.Builder()
            .connectTimeout(90L, TimeUnit.SECONDS)
            .readTimeout(90L, TimeUnit.SECONDS)
            .writeTimeout(90L, TimeUnit.SECONDS)

        // Logger interceptor
        val loggerInterceptor = HttpLoggingInterceptor()
        loggerInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        builder.addInterceptor(loggerInterceptor)

        // Check internet connectivity
        val checkInternetConnectivityInterceptor = Interceptor {
            if (!networkManager.isNetworkAvailable()) {
                throw NoInternetException()
            }
            it.proceed(it.request())
        }
        builder.addInterceptor(checkInternetConnectivityInterceptor)

        // App token
        builder.addInterceptor { chain ->
            val hash = ((System.currentTimeMillis() / 1000).toString() + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY).toMD5()
            var request = chain.request()
            val url = request.url.newBuilder()
                .addQueryParameter("apikey", BuildConfig.PUBLIC_KEY)
                .addQueryParameter("hash", hash)
                .addQueryParameter("ts", (System.currentTimeMillis() / 1000).toString())
                .build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }

        return builder.build()
    }
}