package com.example.assignmentaps.networking

import com.example.assignmentaps.model.RepositoryDetails
import io.reactivex.Observable
import retrofit2.http.GET

open interface Request {

    @GET("developers?language=java&since=weekly")
    fun getAPIResponse(): Observable<ArrayList<RepositoryDetails>>

}