package com.example.assignmentaps.view.viewmodel

import android.arch.lifecycle.ViewModel
import javax.inject.Inject
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.assignmentaps.model.RepositoryDetails
import com.example.assignmentaps.networking.BaseRetrofitBuilder
import com.example.assignmentaps.networking.Request
import com.example.assignmentaps.networking.Response
import com.example.assignmentaps.networking.Status
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class MainActivityViewModel @Inject constructor(var baseRetrofitBuilder: BaseRetrofitBuilder): ViewModel() {

    private var compositeDisposable: CompositeDisposable
    private var repositories: MutableLiveData<Response<ArrayList<RepositoryDetails>>>
    private var response: Response<ArrayList<RepositoryDetails>>

    init {
        compositeDisposable = CompositeDisposable()
        response = Response(Status.LOADING, ArrayList(), null)
        repositories = MutableLiveData()
        repositories.value = response
        loadRepositories()
    }

    fun loadRepositories() {
        baseRetrofitBuilder.buildWithDefault()
            .create(Request::class.java)
            .getAPIResponse()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ArrayList<RepositoryDetails>> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(t: ArrayList<RepositoryDetails>) {
                    response.status = Status.SUCCESS
                    response.data!!.addAll(t)
                    repositories.postValue(response)
                }

                override fun onError(e: Throwable) {
                    response.status = Status.ERROR
                    response.throwable = e
                    repositories.postValue(response)
                }

                override fun onComplete() {}
            })
    }

    fun getRepository(): LiveData<Response<ArrayList<RepositoryDetails>>> {
        return repositories
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}