package com.app.schoolmanagementstudent.login.schoollogin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.schoolmanagementstudent.network.SchoolLoginRepository

class SchoolLoginVIewModelFactory(val schoolLoginRepository: SchoolLoginRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SchoolLoginViewModel(
            schoolLoginRepository
        ) as T
    }
}