package com.app.schoolmanagementstudent.response


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("message")
    val message: String?,
    @SerializedName("response")
    val response: Response?,
    @SerializedName("success")
    val success: Boolean?
) {
    data class Response(
        @SerializedName("date")
        val date: String?,
        @SerializedName("id")
        val id: String?,
        @SerializedName("incharge_id")
        val inchargeId: String?,
        @SerializedName("mark_obtain")
        val markObtain: String?,
        @SerializedName("max_mark")
        val maxMark: String?,
        @SerializedName("roll_no")
        val rollNo: String?,
        @SerializedName("test_id")
        val testId: String?
    )
}