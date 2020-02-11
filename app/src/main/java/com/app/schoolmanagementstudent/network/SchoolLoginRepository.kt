package com.app.schoolmanagementstudent.network

import com.app.schoolmanagement.students.network.SafeApiRequest
import com.app.schoolmanagementstudent.response.SchoolLoginResponse

class SchoolLoginRepository(val myApi: MyApi) : SafeApiRequest() {

    suspend fun schoolLogin(school_name: String, pass: String): SchoolLoginResponse {
        return apiRequest { myApi.school_login(school_name, pass) }
    }
}