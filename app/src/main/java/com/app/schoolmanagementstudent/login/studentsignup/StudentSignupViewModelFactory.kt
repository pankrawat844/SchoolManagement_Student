package com.app.schoolmanagementstudent.login.studentsignup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.schoolmanagementstudent.network.StudentSignupRepository

class StudentSignupViewModelFactory(val schoolSignupRepository: StudentSignupRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StudentSignupViewModel(
            schoolSignupRepository
        ) as T
    }
}