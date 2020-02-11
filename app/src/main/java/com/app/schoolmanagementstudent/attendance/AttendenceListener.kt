package com.app.schoolmanagementstudent.attendance

import com.app.schoolmanagementstudent.response.CheckAttendence
import com.app.schoolmanagementstudent.response.Homework
import com.app.schoolmanagementstudent.response.StudentList

interface AttendenceListener {
    fun onStarted()
    fun onSuccess(data: Homework)
    fun onAllStudentSuccess(data: StudentList)
    fun onCheckAttendence(data: CheckAttendence)
    fun onCheckAttendenceFailour(msg: String)
    fun onFailure(msg: String)
}