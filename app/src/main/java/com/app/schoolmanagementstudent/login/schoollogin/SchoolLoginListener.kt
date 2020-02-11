package com.app.schoolmanagementstudent.login.schoollogin


import com.app.schoolmanagementstudent.response.SchoolLoginResponse

interface SchoolLoginListener {
    fun onStarted()
    fun onFailure(msg: String)
    fun onSuccess(result: SchoolLoginResponse.Response?)
}