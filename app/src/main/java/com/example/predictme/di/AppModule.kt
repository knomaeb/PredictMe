package com.example.predictme.di

import android.content.Context
import com.example.predictme.data.network.NetworkService
import com.example.predictme.utils.network.NetworkConnected
import com.example.predictme.utils.network.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideNetworkService(
        baseUrl: String,
        gsonFactory: GsonConverterFactory,
    ): NetworkService {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonFactory)
            .build()
            .create(NetworkService::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkHelper(
        @ApplicationContext context: Context
    ): NetworkHelper {
        return NetworkConnected(context)
    }

}