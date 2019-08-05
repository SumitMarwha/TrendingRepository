package com.example.assignmentaps.networking;

import android.support.annotation.NonNull


class Response<T> constructor(var status: Status?, var data: T?,
                              var throwable: Throwable?) {

    fun loading(): Response<T> {
        return Response<T>(Status.LOADING, null, null)
    }

    fun success(@NonNull data: T): Response<T> {
        return Response<T>(Status.SUCCESS, data, null)
    }

    fun error(@NonNull error: Throwable): Response<T> {
        return Response<T>(Status.ERROR, null, error)
    }

}
