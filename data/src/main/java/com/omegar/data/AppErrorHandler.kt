package com.omegar.data

import com.omega_r.base.errors.AppException
import com.omega_r.base.errors.ErrorHandler
import com.squareup.moshi.Moshi
import retrofit2.HttpException

class AppErrorHandler(private val moshi: Moshi) : ErrorHandler() {
    override fun handleHttpException(httpException: HttpException, method: String?): AppException {
//        httpException.response()?.errorBody()?.string()?.let { httpErrorBodyAsString ->
//            val apiError = moshi.adapter(ApiErrorBody::class.java).fromJson(httpErrorBodyAsString)
//            return GeneralAppException(apiError?.error_description, httpException)
//        }

        return super.handleHttpException(httpException, method)
    }
}

inline operator fun <R> ErrorHandler.invoke(block: () -> R): R {
    try {
        return block()
    } catch (e: Throwable) {
        throw handleThrowable(e)
    }
}