package com.abdhilabs.coreandroid.data.source

import com.abdhilabs.coreandroid.data.vo.HttpResult
import com.abdhilabs.coreandroid.data.vo.Result
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class RemoteDataSource {
    open suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T
    ): Result<T> {
        return withContext(dispatcher) {
            try {
                Result.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        val result = when (throwable.code()) {
                            401 -> error(
                                HttpResult.UNAUTHENTICATED, throwable.code(),
                                "Unauthenticated"
                            )
                            in 400..451 -> parseHttpError(throwable)
                            in 500..599 -> error(
                                HttpResult.SERVER_ERROR,
                                throwable.code(),
                                "Server Error"
                            )
                            else -> error(
                                HttpResult.NOT_DEFINED,
                                throwable.code(),
                                "Undefined Error"
                            )
                        }
                        result
                    }
                    is UnknownHostException -> error(
                        HttpResult.NO_CONNECTION,
                        null,
                        "No Internet Connection"
                    )
                    is SocketTimeoutException -> error(HttpResult.TIMEOUT, null, "Slow Connection")
                    is IOException -> error(HttpResult.BAD_RESPONSE, null, throwable.message)
                    else -> error(HttpResult.NOT_DEFINED, null, throwable.message)
                }
            }
        }
    }

    private fun parseHttpError(throwable: HttpException): Result<Nothing> {
        return try {
            val errorBody = throwable.response()?.errorBody()?.string() ?: "Unknown HTTP error body"
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val adapter = moshi.adapter(Object::class.java)
            val errorMessage = adapter.fromJson(errorBody)
            error(HttpResult.CLIENT_ERROR, throwable.code(), errorMessage.toString())
        } catch (exception: Exception) {
            error(HttpResult.CLIENT_ERROR, throwable.code(), exception.localizedMessage)
        }
    }

    private fun error(cause: HttpResult, code: Int?, errorMessage: String?): Result.Error {
        return Result.Error(cause, code, errorMessage)
    }
}