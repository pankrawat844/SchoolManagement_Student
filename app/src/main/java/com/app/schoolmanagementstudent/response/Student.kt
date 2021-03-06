package com.app.schoolmanagementstudent.response


import com.google.gson.annotations.SerializedName

data class Student(
    @SerializedName("message")
    val message: String?,
    @SerializedName("response")
    val response: Response? = null,
    @SerializedName("success")
    val success: Boolean?
) {
    data class Response(
        @SerializedName("address")
        val address: String?,
        @SerializedName("gender")
        val gender: String?,
        @SerializedName("guardian_name")
        val guardianName: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("password")
        val password: String?,
        @SerializedName("roll_no")
        val rollNo: String?,
        @SerializedName("school_id")
        val schoolId: String?,
        @SerializedName("school_name")
        val schoolName: String?,
        @SerializedName("student_id")
        val studentId: String?,
        @SerializedName("class_id")
        val class_id: String?,
        @SerializedName("mobile")
        val mobile: String?
    )
}