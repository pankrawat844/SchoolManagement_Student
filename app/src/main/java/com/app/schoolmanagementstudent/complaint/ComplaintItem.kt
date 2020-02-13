package com.app.schoolmanagementstudent.complaint

import com.app.schoolmanagementstudent.R
import com.app.schoolmanagementstudent.databinding.ItemComplaintBinding
import com.app.schoolmanagementstudent.response.ComplaintList
import com.xwray.groupie.databinding.BindableItem

class ComplaintItem(val item: ComplaintList.Response) : BindableItem<ItemComplaintBinding>() {
    override fun getLayout(): Int {
        return R.layout.item_complaint
    }

    override fun bind(viewBinding: ItemComplaintBinding, position: Int) {
        viewBinding.data = item
    }

}