package com.app.schoolmanagementstudent.login.studentlogin

import com.app.schoolmanagementstudent.response.Classes
import com.app.schoolmanagementstudent.response.Student

interface StudentLoginListener {
    fun onStarted()
    fun onSuccess(student: Student.Response)
    fun onFailure(msg: String)
    fun onClassSuccess(classes: Classes)
    fun onSectionSuccess(classes: Classes)
}