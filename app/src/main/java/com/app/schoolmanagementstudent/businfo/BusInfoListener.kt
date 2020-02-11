package com.app.schoolmanagementstudent.businfo

import com.app.schoolmanagementstudent.response.Homework
import com.app.schoolmanagementstudent.response.Timetable

interface BusInfoListener {
    fun onStarted()
    fun onImageSuccess(data: Homework)
    fun onPdfSuccess(data: Homework)
    fun onSuccess(data: Timetable)
    fun onFailure(msg: String)
}