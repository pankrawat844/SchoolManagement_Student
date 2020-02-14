package com.app.schoolmanagementstudent.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.schoolmanagementstudent.network.Repository

class GalleryViewModelFactory(val adminLoginRepository: Repository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GalleryViewModel(
            adminLoginRepository
        ) as T
    }
}