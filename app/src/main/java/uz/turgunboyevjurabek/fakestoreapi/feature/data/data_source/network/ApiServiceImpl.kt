package uz.turgunboyevjurabek.fakestoreapi.feature.data.data_source.network

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.http.CacheControl
import uz.turgunboyevjurabek.fakestoreapi.core.utils.MyResponse
import uz.turgunboyevjurabek.fakestoreapi.feature.domain.madels.MyProduct
import java.nio.channels.UnresolvedAddressException

class ApiServiceImpl(private val client: HttpClient, private val baseUrl: String) : ApiService {
    override suspend fun getProducts(): MyResponse<MyProduct> {
        return try {
            // API ma'lumotlarini olishga urinish
            val response: MyProduct = client.get(
                urlString = "$baseUrl/products"
            ).body()

            Log.d("ApiServiceImpl", "getProducts: $response")
            MyResponse.Success(response)

        } catch (e: RedirectResponseException) { // 3xx xatolar
            MyResponse.Error(e.response.status.description)

        } catch (e: ClientRequestException) { // 4xx xatolar
            MyResponse.Error(e.response.status.description)

        } catch (e: ServerResponseException) { // 5xx xatolar
            MyResponse.Error(e.response.status.description)

        } catch (e: Exception) {
            Log.e("ApiServiceImpl", "Internet yo'q yoki xato yuz berdi: ${e.message}")

            // Internet mavjud bo'lmaganda faqat cache'dan foydalanish
            val cachedResponse = try {
                client.get("$baseUrl/products") {
                    header("Cache-Control", "only-if-cached") // Cache'dan foydalanish
                }.body<MyProduct>()

            } catch (cacheException: Exception) {
                Log.e("ApiServiceImpl", "Cache'dan ma'lumotni o'qib bo'lmadi: ${cacheException.message}")
                null
            }

            // Cache'da ma'lumot bo'lmasa, xabar chiqarish
            cachedResponse?.let {
                Log.d("ApiServiceImpl", "Cache'dan olingan ma'lumot: $it")
                MyResponse.Success(it)
            } ?: MyResponse.Error("Ma'lumotlar topilmadi ${e.message}")
        }
    }
}
