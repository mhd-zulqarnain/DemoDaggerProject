package com.zulqarnain.testproject.di.module

import com.google.gson.GsonBuilder
import com.zulqarnain.testproject.api.MyService
import com.zulqarnain.testproject.data.GenericBodyTypeAdapterFactory
import com.zulqarnain.testproject.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class AppModule {

    @Singleton
    @Provides
    fun provideClient():Retrofit{
        val gson = GsonBuilder()
                .registerTypeAdapterFactory(GenericBodyTypeAdapterFactory.getGenericBodyTypeAdapterFactory())
            .create()

        val client = OkHttpClient.Builder()
//        .addInterceptor(interceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()

    }

    @Singleton
    @Provides
    fun provideService(mRetrofit:Retrofit):MyService{
       return mRetrofit.create(MyService::class.java)
    }
}