package com.app.schoolmanagementstudent.feeinfo

import com.app.schoolmanagementstudent.response.Homework
import com.app.schoolmanagementstudent.response.Timetable

interface FeeInfoListener {
    fun onStarted()
    fun onImageSuccess(data: Homework)
    fun onPdfSuccess(data: Homework)
    fun onSuccess(data: Timetable)
    fun onFailure(msg: String)
}