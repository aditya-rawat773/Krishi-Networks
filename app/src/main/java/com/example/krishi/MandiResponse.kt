package com.example.krishi

data class MandiResponse(
    val code: Int,
    val `data`: Data,
    val status: String
)

data class Data(
        val fav_mandi: List<Any>,
        val other_mandi: List<OtherMandi>
)

data class OtherMandi(
        val crop_id: Int,
        val district: String,
        val district_id: Int,
        val hindi_name: String,
        val id: Int,
        val image: String,
        val km: Double,
        val last_date: String,
        val lat: Double,
        val lng: Double,
        val location: String,
        val market: String,
        val meters: Double,
        val state: String,
        val url_str: String
)