package uz.turgunboyevjurabek.fakestoreapi.core.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import uz.turgunboyevjurabek.fakestoreapi.feature.data.data_source.network.ApiService
import uz.turgunboyevjurabek.fakestoreapi.feature.data.data_source.network.ApiServiceImpl

val networkModule = module {

    single { "https://fakestoreapi.in/api" }

    single {
        HttpClient(CIO){
            install(HttpTimeout){
                requestTimeoutMillis = 15000
                connectTimeoutMillis = 15000
                socketTimeoutMillis = 15000
            }

            install(ContentNegotiation){
                headers {
                    append("Content-Type", "application/json")
                    append("Accept", "*/*")
                }
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

            install(Logging) {
                level = LogLevel.ALL
            }

        }
    }

    single<ApiService> { ApiServiceImpl(get(), get()) }


}