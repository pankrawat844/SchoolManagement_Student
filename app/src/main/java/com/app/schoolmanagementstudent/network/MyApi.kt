package com.app.schoolmanagementstudent.network

import com.app.schoolmanagementstudent.response.*
import com.app.schoolmanagementstudent.utils.Constants
import com.google.gson.GsonBuilder
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface MyApi {

    companion object{
        operator fun invoke():MyApi
        {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val gson = GsonBuilder().setLenient().create()
            return Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Constants.base_url)
                .build()
                .create(MyApi::class.java)
        }
    }


    @FormUrlEncoded
    @POST("school_login.php")
    suspend fun school_login(
        @Field("school_name") school_name: String,
        @Field("password") password: String
    ): Response<SchoolLoginResponse>

    @FormUrlEncoded
    @POST("student_signup.php")
    suspend fun student_signup(
        @Field("school_id") school_id: String,
        @Field("class_name") class_name: String,
        @Field("section_name") section_name: String,
        @Field("roll_no") roll_no: String,
        @Field("password") password: String
    ): Response<Student>

    @FormUrlEncoded
    @POST("student_login.php")
    suspend fun student_login(
        @Field("school_id") school_id: String,
        @Field("roll_no") roll_no: String,
        @Field("password") password: String
    ): Response<Student>


    @FormUrlEncoded
    @POST("class_list.php")
    fun get_classes(@Field("school_id") school_id: String): Call<Classes>

    @FormUrlEncoded
    @POST("section_list.php")
    fun get_Section(@Field("class_name") class_name: String): Call<Classes>

    @FormUrlEncoded
    @POST("edit_profile.php")
    fun edit_profile(
        @Field("student_id") student_id: String,
        @Field("name") name: String,
        @Field("mobile") mobile: String,
        @Field("password") password: String
    ): Call<ResponseBody>


    @FormUrlEncoded
    @POST("teacher_login.php")
    fun teacher_login(
        @Field("userid") teacherid: String,
        @Field("password") password: String,
        @Field("school_id") schoolid: String = "1"
    ): Call<TeacherLogin>

    @FormUrlEncoded
    @POST("homework_list.php")
    fun all_homework(
        @Field("class_id") incharge_id: String

    ):Call<HomeworkList>

    @Multipart
    @POST("homework.php")
    fun homework_upload(
        @Part("incharge_id") incharge_id: RequestBody,
        @Part("date") date:RequestBody,
        @Part("homework_txt") homework_txt:RequestBody,
        @Part img:MultipartBody.Part,
        @Part("type") type:Int=1
        ):Call<Homework>

    @FormUrlEncoded
    @POST("add_notice.php")
    fun add_notice(
        @Field("incharge_id") incharge_id:String,
        @Field("title") title:String,
        @Field("notice") notice: String
    ): Call<Homework>

    @FormUrlEncoded
    @POST("teacher_api/notice_list.php")
    fun all_notice(
        @Field("incharge_id") incharge_id: String

    ): Call<NoticeList>


    @FormUrlEncoded
    @POST("add_complaint.php")
    fun add_complaint(
        @Field("student_id") student_id: String,
        @Field("class_id") class_id: String,
        @Field("title") title: String,
        @Field("complaint") notice: String
    ): Call<Homework>

    @FormUrlEncoded
    @POST("complaint_list.php")
    fun all_complaint(
        @Field("student_id") student_id: String

    ): Call<ComplaintList>

    @FormUrlEncoded
    @POST("add_event.php")
    fun add_event(
        @Field("incharge_id") incharge_id: String,
        @Field("title") title: String,
        @Field("notice") notice: String
    ): Call<Homework>

    @FormUrlEncoded
    @POST("teacher_api/event_list.php")
    fun all_event(
        @Field("incharge_id") incharge_id: String

    ): Call<NoticeList>


    @FormUrlEncoded
    @POST("add_test.php")
    fun add_test(
        @Field("incharge_id") incharge_id: String,
        @Field("title") title: String,
        @Field("date") date: String,
        @Field("info") info: String
    ): Call<Homework>

    @FormUrlEncoded
    @POST("edit_test.php")
    fun update_test(
        @Field("id") id:String,
        @Field("title") title:String,
        @Field("date") date:String,
        @Field("info") info:String
    ):Call<Homework>
    @FormUrlEncoded
    @POST("test_list.php")
    fun all_test(
        @Field("class_id") class_id: String

    ): Call<UpcomingTestList>

    @FormUrlEncoded
    @POST("teacher_api/student_list.php")
    fun all_student(
        @Field("class_name") class_name: String,
        @Field("section_name") section_name: String


    ): Call<StudentList>

    @FormUrlEncoded
    @POST("test_result.php")
    fun get_result(
        @Field("test_id") test_id: String,
        @Field("roll_no") roll_no: String

    ): Call<Result>

    @FormUrlEncoded
    @POST("attendence.php")
    fun add_attendence(
        @Field("date") date: String,
        @Field("class_name") class_id: String,
        @Field("section_name") notice: String,
        @Field("attendence") attendence: String
    ): Call<Homework>

    @FormUrlEncoded
    @POST("student_attendence.php")
    fun check_attendence(
        @Field("class_id") class_id: String
    ): Call<CheckAttendence>

    @Multipart
    @POST("time_table.php")
    fun upload_timetable(
        @Part("class_name") class_name: RequestBody,
        @Part("section_name") section_name: RequestBody,
        @Part img: MultipartBody.Part,
        @Part("type") type: Int = 1
    ): Call<Homework>

    @FormUrlEncoded
    @POST("timetable_list.php")
    fun get_timetable(
        @Field("class_id") class_id: String
    ): Call<Timetable>


    @Multipart
    @POST("businfo.php")
    fun upload_businfo(
        @Part("class_name") class_name: RequestBody,
        @Part("section_name") section_name: RequestBody,
        @Part img: MultipartBody.Part,
        @Part("type") type: Int = 1
    ): Call<Homework>

    @FormUrlEncoded
    @POST("businfo_list.php")
    fun get_busInfo(
        @Field("class_name") class_name: String,
        @Field("section_name") notice: String
    ): Call<Timetable>


    @Multipart
    @POST("leave.php")
    fun upload_leave(
        @Part("class_name") class_name: RequestBody,
        @Part("section_name") section_name: RequestBody,
        @Part img: MultipartBody.Part,
        @Part("type") type: Int = 1
    ): Call<Homework>

    @FormUrlEncoded
    @POST("teacher_api/leave_detail.php")
    fun get_leave(
        @Field("class_name") class_name: String,
        @Field("section_name") notice: String
    ): Call<Timetable>

    @Multipart
    @POST("fee_info.php")
    fun upload_feeInfo(
        @Part("class_name") class_name: RequestBody,
        @Part("section_name") section_name: RequestBody,
        @Part img: MultipartBody.Part,
        @Part("type") type: Int = 1
    ): Call<Homework>

    @FormUrlEncoded
    @POST("fee_info_detail.php")
    fun get_feeInfo(
        @Field("class_name") class_name: String,
        @Field("section_name") notice: String
    ): Call<Timetable>
}