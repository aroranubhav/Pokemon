package com.maxi.pokemon.data.remote

import android.content.Context
import com.maxi.pokemon.model.NetworkResult
import com.maxi.pokemon.utils.Utils
import retrofit2.Response
import java.lang.Exception

abstract class BaseApiResponse(private val context: Context) {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        if (Utils.hasInternetConnection(context)) {
            try {
                val response = apiCall()
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        return NetworkResult.Success(body)
                    }
                }
                return error("${response.code()} ${response.message()}")
            } catch (e: Exception) {
                return error(e.message ?: e.toString())
            }
        }
        return error("No internet connection !")
    }

    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error("Network call failed : $errorMessage")

}