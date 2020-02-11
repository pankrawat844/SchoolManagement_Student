package com.app.schoolmanagementstudent.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.schoolmanagementstudent.network.Repository
import com.app.schoolmanagementstudent.network.StudentRepository

class HomeViewModelFactory(val studentRepository: Repository, val repository: StudentRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(
            studentRepository, repository
        ) as T
    }
}