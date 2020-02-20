package com.app.schoolmanagementstudent.response


import com.google.gson.annotations.SerializedName

data class TeacherLogin(
    @SerializedName("message")
    val message: String?,
    @SerializedName("response")
    val response: Response?,
    @SerializedName("success")
    val success: Boolean?
) {
    data class Response(
        @SerializedName("class_name")
        val className: String?,
        @SerializedName("student_id")
        val id: String?,
        @SerializedName("is_incharge")
        val isIncharge: String?,
        @SerializedName("is_staff")
        val isStaff: String?,
        @SerializedName("namme")
        val name: String?,
        @SerializedName("password")
        val password: String?,
        @SerializedName("mobile")
        val mobile: String?,
        @SerializedName("userid")
        val userid: String?
    )
}