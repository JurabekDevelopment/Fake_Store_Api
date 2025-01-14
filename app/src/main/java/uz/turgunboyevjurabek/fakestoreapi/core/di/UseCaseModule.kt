package uz.turgunboyevjurabek.fakestoreapi.core.di

import org.koin.dsl.module
import uz.turgunboyevjurabek.fakestoreapi.feature.domain.use_case.GetProductsUseCase

val useCaseModule= module {
    single { GetProductsUseCase(get()) }
}