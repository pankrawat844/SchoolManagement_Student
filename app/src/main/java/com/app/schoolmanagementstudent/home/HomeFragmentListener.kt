package com.app.schoolmanagementstudent.home


interface HomeFragmentListener {
    fun onDataChanged(name: String)
    fun onError(msg: String)
    fun onStarted()

}