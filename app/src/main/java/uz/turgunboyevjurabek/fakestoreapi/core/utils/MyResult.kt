package uz.turgunboyevjurabek.fakestoreapi.core.utils


data class MyResult<out T>(val status: Status, val data:T?, val message:String?){

    companion object{
        fun <T>success(data:T): MyResult<T> {
            return MyResult(Status.SUCCESS, data, null)
        }
        fun <T>error(message: String?): MyResult<T> {
            return MyResult(Status.ERROR, null, message)
        }
        fun <T>loading(message: String?): MyResult<T> {
            return MyResult(Status.LOADING, null, message)
        }
        fun <T>idle(): MyResult<T> {
            return MyResult(Status.DEFAULT, null, null)
        }
    }
}