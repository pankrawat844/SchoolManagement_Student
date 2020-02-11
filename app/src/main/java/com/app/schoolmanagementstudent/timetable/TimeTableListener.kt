package com.app.schoolmanagementstudent.timetable

import com.app.schoolmanagementstudent.response.Homework
import com.app.schoolmanagementstudent.response.Timetable

interface TimeTableListener {
    fun onStarted()
    fun onImageSuccess(data: Homework)
    fun onPdfSuccess(data: Homework)
    fun onSuccess(data: Timetable)
    fun onFailure(msg: String)
}