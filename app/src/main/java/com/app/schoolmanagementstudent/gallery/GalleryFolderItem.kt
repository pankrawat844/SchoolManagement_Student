package com.app.schoolmanagementstudent.gallery


import com.app.schoolmanagementstudent.R
import com.app.schoolmanagementstudent.databinding.GalleryFolderItemBinding
import com.app.schoolmanagementstudent.response.Gallery
import com.xwray.groupie.databinding.BindableItem

class GalleryFolderItem(val item: Gallery.Response) : BindableItem<GalleryFolderItemBinding>() {
    override fun getLayout() = R.layout.gallery_folder_item

    override fun bind(viewBinding: GalleryFolderItemBinding, position: Int) {
        viewBinding.data = item
    }
}