package com.app.schoolmanagementstudent.homework

import com.app.schoolmanagementstudent.response.Homework
import com.app.schoolmanagementstudent.response.HomeworkList

interface HomeworkListener {
    fun onStarted()
    fun onSuccess(data: Homework)
    fun onAllHomeworkSuccess(data: HomeworkList)
    fun onFailure(msg: String)
}