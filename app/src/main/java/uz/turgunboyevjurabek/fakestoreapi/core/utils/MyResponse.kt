package uz.turgunboyevjurabek.fakestoreapi.core.utils

sealed class MyResponse<T> {
  data class Success<T>(val data: T) : MyResponse<T>()
  data class Error<T>(val message: String) : MyResponse<T>()
}
