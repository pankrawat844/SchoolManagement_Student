package com.app.schoolmanagementstudent.notice

import com.app.schoolmanagementstudent.response.Homework
import com.app.schoolmanagementstudent.response.NoticeList

interface NoticeListener {
    fun onStarted()
    fun onSuccess(data: Homework)
    fun onAllNoticeSuccess(data: NoticeList)
    fun onFailure(msg: String)
}