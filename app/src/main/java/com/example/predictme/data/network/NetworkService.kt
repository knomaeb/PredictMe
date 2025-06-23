package com.example.predictme.data.network

import com.example.predictme.model.PersonAge
import com.example.predictme.model.PersonGender
import com.example.predictme.model.PersonNationality
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkServiceForAge{
    @GET("https://api.agify.io")
    suspend fun getAge(
        @Query("name") name: String
    ) : PersonAge
}

@Singleton
interface NetworkServiceForGender {
    @GET("https://api.genderize.io")
    suspend fun getGender(
        @Query("name") name: String
    ) : PersonGender
}

@Singleton
interface NetworkServiceForNationality{
    @GET("https://api.nationalize.io")
    suspend fun getNationality(
        @Query("name") name: String
    ) : PersonNationality
}