package com.pas.eater.di.data

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import com.pas.eater.data.data_sourse.api.ApiService
import com.pas.eater.data.data_sourse.api.helper.RemoteException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val BASE_URL = "http://run.mocky.io"

private const val CONTENT_TYPE = "text/plain"
private const val CONNECT_TIMEOUT = 30L
private const val WRITE_TIMEOUT = 30L
private const val READ_TIMEOUT = 30L


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun getGsonConverterFactory(): GsonConverterFactory {
        val gson = GsonBuilder().serializeNulls().setLenient().create()
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun getLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun getHeaderInterceptor(): Interceptor {
        return Interceptor {chain->
            try {
                val newRequest =
                    chain.request().newBuilder()
                        .addHeader("content-type", CONTENT_TYPE)
                        .build()
                val response = chain.proceed(newRequest)
                if(HttpURLConnection.HTTP_BAD_REQUEST <= response.code && response.code <= 499) {
                    throw RemoteException.ClientError(response.message)
                } else if(HttpURLConnection.HTTP_INTERNAL_ERROR <= response.code && response.code <= 599) {
                    throw RemoteException.ServerError(response.message)
                }
                response
            } catch(e: Exception) {
                throw when(e) {
                    is UnknownHostException->RemoteException.NoNetworkError(e.message.toString())
                    is SocketTimeoutException->RemoteException.NoNetworkError(e.message.toString())
                    is RemoteException->e
                    else->RemoteException.GenericError(e.message.toString())
                }
            }
        }
    }

    @Provides
    @Singleton
    fun getOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: Interceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(headerInterceptor)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun getRetrofit(
        converterFactory: GsonConverterFactory, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideETISApiService(
        retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}