package com.github.rixspi.simpleflickrgallery.di.base

import com.github.rixspi.simpleflickrgallery.BuildConfig
import com.github.rixspi.simpleflickrgallery.repository.net.FlickrApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.text.DateFormat
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val MAX_TIMEOUT = 60L

@Module
class NetModule {
    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
            .setDateFormat(DateFormat.FULL, DateFormat.FULL)
            .create()

    @Provides
    @Singleton
    //fun provideOkHttpClient(appSettings: AppSettings): OkHttpClient {
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }

        builder.addInterceptor { chain ->
            val original = chain.request()
            val token = null

            if (!token.isNullOrEmpty()) {
                val request = original.newBuilder()
                        //.header(HEADER_AUTHORIZATION, token)
                        .method(original.method(), original.body())
                        .build()

                try {
                    return@addInterceptor chain.proceed(request)
                } catch (ioe: IOException) {
                    return@addInterceptor chain.proceed(original.newBuilder().build())
                }

            } else {
                return@addInterceptor chain.proceed(original.newBuilder().build())
            }
        }

        builder.connectTimeout(MAX_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(MAX_TIMEOUT, TimeUnit.SECONDS)

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(BuildConfig.API_URL)
                    .client(client)
                    .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): FlickrApi = retrofit.create(FlickrApi::class.java)
}