package uz.turgunboyevjurabek.fakestoreapi.feature.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import uz.turgunboyevjurabek.fakestoreapi.core.utils.MyResponse
import uz.turgunboyevjurabek.fakestoreapi.core.utils.MyResult
import uz.turgunboyevjurabek.fakestoreapi.feature.domain.madels.MyProduct
import uz.turgunboyevjurabek.fakestoreapi.feature.domain.use_case.GetProductsUseCase

class GetProductsViewModel(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {
    private val _getData = MutableStateFlow<MyResult<MyProduct?>>(MyResult.idle())
    val getData = _getData.asStateFlow()

    init {

        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            _getData.value = MyResult.loading("Loading") // Loading holatini belgilash
            try {
                val result = getProductsUseCase()
                _getData.value = MyResult.success(result)
            } catch (e: Exception) {
                _getData.value = MyResult.error(e.message ?: "Noma'lum xato yuz berdi")
            }
        }
    }

}