package com.kvsoftware.dependencyinjectionhilt.domain.helper

import android.content.Context
import com.kvsoftware.dependencyinjectionhilt.R
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ErrorHelper {

    fun getErrorMessage(context: Context, throwable: Throwable): String {
        return when (throwable) {
            is HttpException -> {
                context.getString(R.string.error_something_went_wrong)
            }
            is UnknownHostException, is ConnectException, is SocketTimeoutException -> {
                context.getString(R.string.error_internet_connection)
            }
            else -> context.getString(R.string.error_something_went_wrong)
        }
    }

    fun isRestUnauthorized(throwable: Throwable): Boolean =
        throwable is HttpException && throwable.code() == 401

    fun isRestNotFound(throwable: Throwable): Boolean =
        throwable is HttpException && throwable.code() == 404

    fun isRestWrongProcess(throwable: Throwable): Boolean =
        throwable is HttpException && throwable.code() == 422

}