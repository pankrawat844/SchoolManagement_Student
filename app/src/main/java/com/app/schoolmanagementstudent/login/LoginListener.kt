package com.app.schoolmanagementstudent.login

import com.app.schoolmanagementstudent.response.TeacherLogin

interface LoginListener {
    fun onStarted()
    fun onSuccess(data:TeacherLogin.Response)
    fun onFailure(msg:String)
}