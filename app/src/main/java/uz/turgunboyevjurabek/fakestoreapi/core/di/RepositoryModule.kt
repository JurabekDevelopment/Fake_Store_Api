package uz.turgunboyevjurabek.fakestoreapi.core.di

import org.koin.dsl.module
import uz.turgunboyevjurabek.fakestoreapi.feature.data.repository_impl.MyRepositoryImpl
import uz.turgunboyevjurabek.fakestoreapi.feature.domain.repository.MyRepository

val repositoryModule= module {
    single<MyRepository> { MyRepositoryImpl(get()) }
}