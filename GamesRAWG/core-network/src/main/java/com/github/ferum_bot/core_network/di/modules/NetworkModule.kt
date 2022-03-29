package com.github.ferum_bot.core_network.di.modules

import com.github.ferum_bot.core_network.BuildConfig
import com.github.ferum_bot.core_network.api.ApiKeyInterceptor
import com.github.ferum_bot.core_network.api.RAWGApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by Matvey Popov.
 * Date: 08.02.2021
 * Time: 22:47
 * Project: Games-RAWG
 */
@Module
open class NetworkModule {

    @Provides
    @Singleton
    fun provideGamesApi(retrofit: Retrofit): RAWGApi
        = retrofit.create(RAWGApi::class.java)

    @Provides
    fun provideRetrofit(converterFactory: MoshiConverterFactory, client: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(converterFactory)
        .baseUrl(BASE_URL)
        .client(client)
        .build()

    @Provides
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory.create(moshi)

    @Provides
    fun provideClient(interceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor(ApiKeyInterceptor())
        .build()

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        }
        else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    fun provideMoshi(adapterFactory: KotlinJsonAdapterFactory): Moshi = Moshi.Builder()
        .add(adapterFactory)
        .build()

    @Provides
    fun provideKotlinJSONAdapterFactory(): KotlinJsonAdapterFactory
        = KotlinJsonAdapterFactory()

    companion object {
        private const val BASE_URL = "https://api.rawg.io/"
    }
}