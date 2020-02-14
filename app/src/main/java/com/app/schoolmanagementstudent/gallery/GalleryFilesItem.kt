package com.app.schoolmanagementstudent.gallery


import com.app.schoolmanagementstudent.R
import com.app.schoolmanagementstudent.databinding.ItemGalleryBinding
import com.app.schoolmanagementstudent.response.Gallery
import com.xwray.groupie.databinding.BindableItem

class GalleryFilesItem(val item: Gallery.Response) : BindableItem<ItemGalleryBinding>() {
    override fun getLayout() = R.layout.item_gallery

    override fun bind(viewBinding: ItemGalleryBinding, position: Int) {
        viewBinding.data = item
    }
}