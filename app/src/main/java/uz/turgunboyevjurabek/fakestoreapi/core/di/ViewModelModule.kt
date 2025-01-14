package uz.turgunboyevjurabek.fakestoreapi.core.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.view_model.GetProductsViewModel

val viewModelModule = module {
    viewModel { GetProductsViewModel(get()) }
}