package com.app.schoolmanagementstudent.response


import com.google.gson.annotations.SerializedName

data class SchoolLoginResponse(
    @SerializedName("response")
    val response: Response? = null,
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("message")
    val message: String?
) {
    data class Response(
        @SerializedName("id")
        val id: String? = null,
        @SerializedName("logo")
        val logo: String? = null,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("password")
        val password: String? = null
    )
}