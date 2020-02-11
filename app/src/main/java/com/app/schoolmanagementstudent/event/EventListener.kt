package com.app.schoolmanagementstudent.event

import com.app.schoolmanagementstudent.response.Homework
import com.app.schoolmanagementstudent.response.NoticeList

interface EventListener {
    fun onStarted()
    fun onSuccess(data: Homework)
    fun onAllNoticeSuccess(data: NoticeList)
    fun onFailure(msg: String)
}