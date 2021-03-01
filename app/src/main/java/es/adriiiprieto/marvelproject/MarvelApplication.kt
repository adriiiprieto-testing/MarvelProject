package es.adriiiprieto.marvelproject

import android.app.Application
import es.adriiiprieto.marvelproject.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MarvelApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@MarvelApplication)
            modules(dataModule)
        }
    }
}