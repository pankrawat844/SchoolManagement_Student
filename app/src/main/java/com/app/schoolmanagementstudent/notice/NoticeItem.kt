package com.app.schoolmanagementstudent.notice

import com.app.schoolmanagementstudent.R
import com.app.schoolmanagementstudent.databinding.ItemNoticeBinding
import com.app.schoolmanagementstudent.response.NoticeList
import com.xwray.groupie.databinding.BindableItem

class NoticeItem(val item:NoticeList.Response) :BindableItem<ItemNoticeBinding>()
{
    override fun getLayout(): Int {
        return R.layout.item_notice
    }

    override fun bind(viewBinding: ItemNoticeBinding, position: Int) {
         viewBinding.data=item
    }

}