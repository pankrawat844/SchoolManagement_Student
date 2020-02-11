package com.app.schoolmanagementstudent.upcomingtest

import com.app.schoolmanagementstudent.response.Homework
import com.app.schoolmanagementstudent.response.UpcomingTestList

interface TestListener {
    fun onStarted()
    fun onSuccess(data: Homework)
    fun onAllTestSuccess(data: UpcomingTestList)
    fun onFailure(msg: String)
}