package uz.turgunboyevjurabek.fakestoreapi.feature.data.data_source.network

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import uz.turgunboyevjurabek.fakestoreapi.core.utils.MyResponse
import uz.turgunboyevjurabek.fakestoreapi.feature.domain.madels.MyProduct

class ApiServiceImpl(private val client: HttpClient, private val baseUrl: String) : ApiService {
    override suspend fun getProducts(): MyResponse<MyProduct> {
        return try {

            val response = client.get(
                urlString = "$baseUrl/products",
            ).body<MyProduct>()
            Log.d("ApiServiceImpl", "getProducts: $response")

            return MyResponse.Success(response)
        } catch (e: RedirectResponseException) { // 3xx
            MyResponse.Error(e.response.status.description)
        } catch (e: ClientRequestException) { // 4xx
            MyResponse.Error(e.response.status.description)
        } catch (e: ServerResponseException) { // 5xx
            MyResponse.Error(e.response.status.description)
        } catch (e: Exception) {
            Log.e("ApiServiceImpl", "Unexpected error: ${e.message}")
            MyResponse.Error(e.stackTraceToString() ?: "Noma'lum xato")
        }

    }

}