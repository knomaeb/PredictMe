package com.example.predictme.data.repository

import com.example.predictme.data.network.NetworkServiceForAge
import com.example.predictme.data.network.NetworkServiceForGender
import com.example.predictme.data.network.NetworkServiceForNationality
import com.example.predictme.model.CombinedData
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val networkServiceForAge: NetworkServiceForAge,
    private val networkServiceForGender: NetworkServiceForGender,
    private val networkServiceForNationality: NetworkServiceForNationality
) {
    suspend fun getCombinedData(
        name: String
    ): Result<CombinedData>{
        return try {
            coroutineScope {
                val age = async { networkServiceForAge.getAge(name) }
                val gender = async { networkServiceForGender.getGender(name) }
                val nationality = async { networkServiceForNationality.getNationality(name) }

                Result.success(
                    CombinedData(
                        PersonAge = age.await(),
                        PersonGender = gender.await(),
                        PersonNationality = nationality.await()
                    )
                )

            }
        } catch (e: Exception){
            Result.failure(e)
        }

    }

}