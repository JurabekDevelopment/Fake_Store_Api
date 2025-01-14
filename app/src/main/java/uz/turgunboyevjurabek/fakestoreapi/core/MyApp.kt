package uz.turgunboyevjurabek.fakestoreapi.core

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import uz.turgunboyevjurabek.fakestoreapi.core.di.networkModule
import uz.turgunboyevjurabek.fakestoreapi.core.di.repositoryModule
import uz.turgunboyevjurabek.fakestoreapi.core.di.useCaseModule
import uz.turgunboyevjurabek.fakestoreapi.core.di.viewModelModule

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }

}