package com.example.dnd.networking.di

import android.util.Log
import com.example.dnd.networking.DndRepository
import com.example.dnd.networking.DndService
import com.example.dnd.networking.RequestInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {
    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://www.dnd5eapi.co/api/2014/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesDndService(retrofit: Retrofit): DndService {
        return retrofit.create(DndService::class.java)
    }

    @Provides
    @Singleton
    fun providesDndRepository(
        dndService: DndService
    ): DndRepository {
        return DndRepository(
            dndService
        )
    }
}
