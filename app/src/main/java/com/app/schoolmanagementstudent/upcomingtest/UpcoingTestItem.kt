package com.app.schoolmanagementstudent.upcomingtest

import com.app.schoolmanagementstudent.R
import com.app.schoolmanagementstudent.databinding.ItemTestBinding
import com.app.schoolmanagementstudent.response.UpcomingTestList
import com.xwray.groupie.databinding.BindableItem

class UpcoingTestItem(val item: UpcomingTestList.Response) : BindableItem<ItemTestBinding>() {
    override fun getLayout(): Int {
        return R.layout.item_test
    }

    override fun bind(viewBinding: ItemTestBinding, position: Int) {
        viewBinding.data = item
    }

}