package com.example.predictme.model

import com.google.gson.annotations.SerializedName

data class PersonAge(

    @SerializedName("count")
    val count: Int,

    @SerializedName("name")
    val name : String,

    @SerializedName("age")
    val age: String,

)

data class PersonGender(

    @SerializedName("count")
    val count: Int,

    @SerializedName("name")
    val name : String,

    @SerializedName("gender")
    val gender: String,

    @SerializedName("probability")
    val probability : Long

)

data class PersonNationality(

    @SerializedName("count")
    val count: Int,

    @SerializedName("name")
    val name : String,

    @SerializedName("gender")
    val country : List<Country>

)

data class Country(
    @SerializedName("country_id")
    val country_id : String,

    @SerializedName("probability")
    val probability : Long
)

data class CombinedData(
    val PersonAge : PersonAge,
    val PersonGender : PersonGender,
    val PersonNationality : PersonNationality
)
