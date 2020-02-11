package com.app.schoolmanagementstudent.response


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class CheckAttendence(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("response")
    val response: List<Response>,
    @SerializedName("success")
    val success: Boolean
) {
    @Keep
    data class Response(
        @SerializedName("attendence")
        val attendence: List<Attendence>,
        @SerializedName("date")
        val date: String
    ) {
        @Keep
        data class Attendence(
            @SerializedName("name")
            val name: String?,
            @SerializedName("attendence")
            val attendence: String,
            @SerializedName("roll_no")
            val rollNo: String
        )
    }
}