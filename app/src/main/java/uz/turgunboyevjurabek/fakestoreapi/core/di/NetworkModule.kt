package uz.turgunboyevjurabek.fakestoreapi.core.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.cache.storage.FileStorage
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import uz.turgunboyevjurabek.fakestoreapi.feature.data.data_source.network.ApiService
import uz.turgunboyevjurabek.fakestoreapi.feature.data.data_source.network.ApiServiceImpl
import java.io.File
val networkModule = module {

    single { "https://fakestoreapi.in/api" }

    single {
        HttpClient(CIO) {
            // So'rovlar uchun umumiy sozlamalar
            defaultRequest {
                headers {
                    append("Content-Type", "application/json")
                    append("Accept", "*/*")
                }
            }

            // Timeout sozlamalari
            install(HttpTimeout) {
                requestTimeoutMillis = 15000
                connectTimeoutMillis = 15000
                socketTimeoutMillis = 15000
            }

            // Cache sozlamalari
            install(HttpCache) {
                val cacheDir = File(androidApplication().cacheDir, "http_cache")
                cacheDir.mkdirs()
                publicStorage(FileStorage(cacheDir)) // Cache papkasini belgilash
            }

            // JSON serializatsiya uchun
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

            // Logging
            install(Logging) {
                level = LogLevel.ALL
            }
        }
    }

    single<ApiService> { ApiServiceImpl(get(), get()) }
}
