package com.app.schoolmanagementstudent.complaint

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.schoolmanagementstudent.network.Repository

class ComplaintViewmodelFactory(val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ComplaintViewmodel(repository) as T
    }
}