package com.app.schoolmanagementstudent.network

import com.app.schoolmanagement.students.network.SafeApiRequest
import com.app.schoolmanagementstudent.response.Homework

import com.app.schoolmanagementstudent.response.Student
import com.app.schoolmanagementstudent.response.Timetable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Part

class StudentRepository(val api: MyApi) : SafeApiRequest() {
    suspend fun getStudentDetails(school_id: String, roll_no: String, password: String): Student {
        return apiRequest { api.student_login(school_id, roll_no, password) }
    }

    suspend fun edit_profile(
        name: String,
        mobile: String,
        password: String,
        student_id: String
    ): Call<ResponseBody> {
        return api.edit_profile(student_id, name, mobile, password)
    }

    suspend fun uploadTimetable(
        @Part("class_name") class_id: RequestBody,
        @Part("section_name") section_name: RequestBody,
        img: MultipartBody.Part
    ): Call<Homework> {
        return api.upload_timetable(class_id, section_name, img)
    }

    suspend fun getTimetable(
        class_name: String
    ): Call<Timetable> {
        return api.get_timetable(class_name)
    }

    suspend fun uploadBusInfo(
        @Part("class_name") class_id: RequestBody,
        @Part("section_name") section_name: RequestBody,
        img: MultipartBody.Part
    ): Call<Homework> {
        return api.upload_businfo(class_id, section_name, img)
    }


    suspend fun uploadLeave(
        @Part("class_name") class_id: RequestBody,
        @Part("section_name") section_name: RequestBody,
        img: MultipartBody.Part
    ): Call<Homework> {
        return api.upload_leave(class_id, section_name, img)
    }

    suspend fun getLeave(
        class_name: String,
        section_name: String
    ): Call<Timetable> {
        return api.get_leave(class_name, section_name)
    }

    suspend fun uploadFeeInfo(
        @Part("class_name") class_id: RequestBody,
        @Part("section_name") section_name: RequestBody,
        img: MultipartBody.Part
    ): Call<Homework> {
        return api.upload_feeInfo(class_id, section_name, img)
    }


}