package com.app.schoolmanagementstudent.response


import com.google.gson.annotations.SerializedName

data class Gallery(
    @SerializedName("response")
    val response: List<Response>?
) {
    data class Response(
        @SerializedName("index")
        val index: Int?,
        @SerializedName("value")
        val value: String?
    )
}