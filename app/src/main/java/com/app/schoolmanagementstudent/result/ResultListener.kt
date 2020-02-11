package com.app.schoolmanagementstudent.result

import com.app.schoolmanagementstudent.response.Homework
import com.app.schoolmanagementstudent.response.StudentList
import com.app.schoolmanagementstudent.response.UpcomingTestList

interface ResultListener {
    fun onStarted()
    fun onSuccess(data: Homework)
    fun onAllStudentSuccess(data: StudentList)
    fun onAllTestSuccess(data: UpcomingTestList)
    fun onFailure(msg: String)
}