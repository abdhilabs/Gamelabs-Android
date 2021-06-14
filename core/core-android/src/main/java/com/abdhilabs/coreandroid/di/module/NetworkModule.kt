package com.abdhilabs.coreandroid.di.module

import android.content.Context
import com.abdhilabs.coreandroid.BuildConfig
import com.abdhilabs.coreandroid.data.network.NetworkInterceptor
import com.abdhilabs.coreandroid.utils.preference.EncryptedPreference
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
open class NetworkModule {

    companion object {
        private const val DEFAULT_CACHE_FILE_NAME = "okhttp-cache"
        private const val DEFAULT_CACHE_SIZE: Long = 1024 * 1024 // 1 MB
        private const val DEFAULT_CONNECT_TIME_OUT: Long = 30 * 1000
        private const val DEFAULT_READ_TIME_OUT: Long = 30 * 1000
    }

    @Provides
    @Singleton
    fun provideHttpCache(context: Context): Cache {
        val file = File(context.filesDir, DEFAULT_CACHE_FILE_NAME)
        return Cache(file, DEFAULT_CACHE_SIZE)
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideInterceptor(pref: EncryptedPreference): NetworkInterceptor = NetworkInterceptor(pref)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        cache: Cache,
        interceptor: NetworkInterceptor
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.cache(cache)
        client.connectTimeout(DEFAULT_CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
        client.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.MILLISECONDS)
        client.addInterceptor(interceptor)
        if (BuildConfig.DEBUG) {
            client.addInterceptor(loggingInterceptor)
        }
        return client.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun getMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
}