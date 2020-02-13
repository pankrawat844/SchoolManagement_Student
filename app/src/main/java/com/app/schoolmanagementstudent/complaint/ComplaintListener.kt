package com.app.schoolmanagementstudent.complaint

import com.app.schoolmanagementstudent.response.ComplaintList
import com.app.schoolmanagementstudent.response.Homework

interface ComplaintListener {
    fun onStarted()
    fun onSuccess(data: Homework)
    fun onAllNoticeSuccess(data: ComplaintList)
    fun onFailure(msg: String)
}