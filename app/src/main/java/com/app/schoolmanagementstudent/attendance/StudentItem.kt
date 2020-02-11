package com.app.schoolmanagementstudent.attendance

import com.app.schoolmanagementstudent.R
import com.app.schoolmanagementstudent.databinding.ItemAttendenceBinding
import com.app.schoolmanagementstudent.response.StudentList
import com.xwray.groupie.databinding.BindableItem

class StudentItem(val item:StudentList.Response):BindableItem<ItemAttendenceBinding>() {
    override fun getLayout(): Int =R.layout.item_attendence

    override fun bind(viewBinding: ItemAttendenceBinding, position: Int) {
            viewBinding.data=item
    }
}