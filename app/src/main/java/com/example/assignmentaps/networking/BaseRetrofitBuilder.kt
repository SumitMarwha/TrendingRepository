package com.example.assignmentaps.networking;

import com.example.assignmentaps.BuildConfig
import com.example.assignmentaps.base.AssignmentAPSApplication
import com.example.assignmentaps.base.utils.UtilString
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.apache.http.params.CoreConnectionPNames.CONNECTION_TIMEOUT
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class BaseRetrofitBuilder {

    @Volatile
    private var retrofitBuilder: Retrofit.Builder? = null

    @Volatile
    private var okHttpBuilder: OkHttpClient.Builder? = null


    private fun getRetrofitBuilder(): Retrofit.Builder? {
        if (retrofitBuilder == null) {
            synchronized(Retrofit.Builder::class.java) {
                val gson = GsonBuilder().setLenient()
                        .create()
                retrofitBuilder = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
            }
        }
        return retrofitBuilder
    }

    fun buildWithDefault(): Retrofit {
        return getRetrofitBuilder()!!
                .baseUrl(UtilString.BaseUrl)
                .client(getClient(0))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    private fun getClient(timeoutSecs: Int): okhttp3.OkHttpClient {
        if (okHttpBuilder == null) {
            synchronized(OkHttpClient.Builder::class.java) {
                okHttpBuilder = OkHttpClient.Builder()
            }
            addLogging()
            addCache()
        }

        if (timeoutSecs > 0) {
            okHttpBuilder!!.connectTimeout(timeoutSecs.toLong(), TimeUnit.SECONDS)
            okHttpBuilder!!.readTimeout(timeoutSecs.toLong(), TimeUnit.SECONDS)
        }
        okHttpBuilder!!.retryOnConnectionFailure(true)
        val client = okHttpBuilder!!.build()
        dispatcher = client.dispatcher()
        return client
    }

    private fun addLogging() {
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpBuilder!!.addInterceptor(loggingInterceptor)
        }
    }

    private fun addCache() {
        val cacheDirectory = File(AssignmentAPSApplication.instance.getCacheDir().getAbsolutePath(), CACHE_DIRECTORY_NAME)
        val cache = Cache(cacheDirectory, CACHE_SIZE)
        okHttpBuilder!!.cache(cache)

    }

    companion object {

        val CACHE_DIRECTORY_NAME = "HttpCache"

        val CACHE_SIZE = (5 * 1024 * 1024).toLong()

        private var dispatcher: Dispatcher? = null
    }
}
