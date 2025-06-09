package com.example.predictme.data.network

import com.example.predictme.model.PersonAge
import com.example.predictme.model.PersonGender
import com.example.predictme.model.PersonNationality
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET()
    suspend fun getAge(
        @Query("name") name: String
    ) : PersonAge

    @GET()
    suspend fun getGender(
        @Query("name") name: String
    ) : PersonGender

    @GET()
    suspend fun getNationality(
        @Query("name") name: String
    ) : PersonNationality
}