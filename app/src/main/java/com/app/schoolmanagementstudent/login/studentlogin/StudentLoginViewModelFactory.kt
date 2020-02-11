package com.app.schoolmanagementstudent.login.studentlogin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.schoolmanagementstudent.network.StudentRepository

class StudentLoginViewModelFactory(val schoolRepository: StudentRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StudentLoginViewModel(
            schoolRepository
        ) as T
    }
}