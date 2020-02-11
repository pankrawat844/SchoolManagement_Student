package com.app.schoolmanagementstudent.complaint

import com.app.schoolmanagementstudent.response.Homework
import com.app.schoolmanagementstudent.response.NoticeList

interface ComplaintListener {
    fun onStarted()
    fun onSuccess(data: Homework)
    fun onAllNoticeSuccess(data: NoticeList)
    fun onFailure(msg: String)
}