package cy.com.currenciesexample.models

import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.UnknownHostException

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Error(val errorResponse: ErrorResponse) : ResultWrapper<Nothing>()
}

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    val errorResponse = convertErrorBody(throwable)
                    ResultWrapper.Error(errorResponse)
                }
                is UnknownHostException -> {
                    ResultWrapper.Error(ErrorResponse(500, "", "Network Error"))
                }
                else -> {
                    ResultWrapper.Error(ErrorResponse())
                }
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): ErrorResponse {
    return try {
        Gson().fromJson(throwable.response()?.errorBody()?.toString(), ErrorResponse::class.java)
    } catch (exception: Exception) {
        ErrorResponse()
    }
}