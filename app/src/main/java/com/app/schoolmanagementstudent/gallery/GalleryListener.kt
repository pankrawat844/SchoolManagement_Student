package com.app.schoolmanagementstudent.gallery

import com.app.schoolmanagementstudent.response.Gallery


interface GalleryListener {
    fun onStarted()
    fun onAllgallerySuccess(data: Gallery)
    fun onSuccess(data: String)
    fun onFailure(msg: String)
}