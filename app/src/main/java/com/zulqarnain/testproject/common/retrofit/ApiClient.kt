package com.zulqarnain.testproject.common.retrofit

import com.zulqarnain.testproject.api.MyService
import com.zulqarnain.testproject.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient private constructor() {
//    var interceptor = HttpLoggingInterceptor()

    val client = OkHttpClient.Builder()
//        .addInterceptor(interceptor)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    val mRetrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    var myService: MyService

    companion object {
        lateinit var mRetrofitClient: ApiClient
        fun getInstance(): ApiClient {
            if (!::mRetrofitClient.isInitialized)
                mRetrofitClient = ApiClient()
            return mRetrofitClient
        }
    }

    init {
        myService = mRetrofit.create(MyService::class.java)
    }

    fun getService(): MyService {
        return myService
    }
}