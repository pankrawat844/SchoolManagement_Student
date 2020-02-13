package com.app.schoolmanagementstudent.response


import com.google.gson.annotations.SerializedName

data class ComplaintList(
    @SerializedName("message")
    val message: String?,
    @SerializedName("response")
    val response: List<Response>? = null,
    @SerializedName("success")
    val success: Boolean?
) {
    data class Response(
        @SerializedName("date")
        val date: String?,
        @SerializedName("id")
        val id: String?,
        @SerializedName("student_id")
        val student_id: String?,
        @SerializedName("class_id")
        val class_id: String?,
        @SerializedName("complaint")
        val complaint: String?,
        @SerializedName("title")
        val title: String?
    )
}