package com.example.predictme.model

import com.google.gson.annotations.SerializedName

data class PersonAge(

    val count: Int,

    val name : String,

    val age: String,

)

data class PersonGender(

    val count: Int,

    val name : String,

    val gender: String,

    val probability : Double

)

data class PersonNationality(

    val count: Int,

    val name : String,

    val country : List<Country>

)

data class Country(
    val country_id : String,

    val probability : Double
)

data class CombinedData(
    val PersonAge : PersonAge,
    val PersonGender : PersonGender,
    val PersonNationality : PersonNationality
)
