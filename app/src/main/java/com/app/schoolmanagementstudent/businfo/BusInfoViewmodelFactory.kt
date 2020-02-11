package com.app.schoolmanagementstudent.timetable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.schoolmanagementstudent.businfo.BusInfoViewmodel
import com.app.schoolmanagementstudent.network.Repository

class BusInfoViewmodelFactory(val repository: Repository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BusInfoViewmodel(repository) as T
    }
}