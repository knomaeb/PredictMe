package com.example.predictme.di

import android.content.Context
import com.example.predictme.data.network.NetworkServiceForAge
import com.example.predictme.data.network.NetworkServiceForGender
import com.example.predictme.data.network.NetworkServiceForNationality
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
object AppModule {

    private const val BASE_URL_AGE = "https://api.agify.io"
    private const val BASE_URL_GENDER = "https://api.genderize.io"
    private const val BASE_URL_NATIONALITY = "https://api.nationalize.io"

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofitForAge(
        gsonFactory: GsonConverterFactory,
    ): NetworkServiceForAge {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_AGE)
            .addConverterFactory(gsonFactory)
            .build()
            .create(NetworkServiceForAge::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofitForGender(
        gsonFactory: GsonConverterFactory,
    ): NetworkServiceForGender {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_GENDER)
            .addConverterFactory(gsonFactory)
            .build()
            .create(NetworkServiceForGender::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofitForNationality(
        gsonFactory: GsonConverterFactory,
    ): NetworkServiceForNationality {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_NATIONALITY)
            .addConverterFactory(gsonFactory)
            .build()
            .create(NetworkServiceForNationality::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkHelper(
        @ApplicationContext context: Context
    ): NetworkHelper {
        return NetworkConnected(context)
    }

}