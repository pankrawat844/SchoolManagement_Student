package com.app.schoolmanagementstudent.gallery

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.app.schoolmanagementstudent.R
import com.app.schoolmanagementstudent.response.Gallery
import com.app.schoolmanagementstudent.utils.RecyclerItemClickListenr
import com.app.schoolmanagementstudent.utils.hide
import com.app.schoolmanagementstudent.utils.show
import com.app.schoolmanagementstudent.utils.toast


import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_gallery.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class GalleryActivity : AppCompatActivity(), KodeinAware, GalleryListener {
    val factory: GalleryViewModelFactory by instance()
    private lateinit var galleryViewModel: GalleryViewModel
    private var bottomSheetBehavior: BottomSheetBehavior<View>? = null
    var list: List<Gallery.Response>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_gallery)
        galleryViewModel = ViewModelProviders.of(this, factory).get(GalleryViewModel::class.java)
        galleryViewModel.listener = this
        galleryViewModel.getAllFolder()
//        bottomSheetBehavior = BottomSheetBehavior.from(bottomsheet_addfolder)
//        bottomSheetBehavior?.state = BottomSheetBehavior.STATE_HIDDEN
//        menu.setOnClickListener {
//            if (bottomSheetBehavior?.state != BottomSheetBehavior.STATE_EXPANDED) {
//                bottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
//            }
//        }
//
//        buttonOk.setOnClickListener {
//            if (!folder_name.text.toString().isNullOrEmpty()) {
//                galleryViewModel?.addNewFolder(folder_name.text.toString())
//            } else {
//                toast("Please enter folder name")
//            }
//        }

        recylerview_gallery.addOnItemTouchListener(
            RecyclerItemClickListenr(
                this,
                recylerview_gallery,
                object : RecyclerItemClickListenr.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        Intent(this@GalleryActivity, GalleryItemActivity::class.java).also {
                            it.putExtra("folder_name", list?.get(position)?.value)
                            startActivity(it)
                        }
                    }

                    override fun onItemLongClick(view: View?, position: Int) {

                    }

                })
        )

    }

    override val kodein by kodein()

    override fun onStarted() {
        gallery_progress.show()

    }

    override fun onAllgallerySuccess(data: Gallery) {
        list = data.response!!
        gallery_progress.hide()
        initRecylerview(data.response.changeItem())
    }

    private fun initRecylerview(item: List<GalleryFolderItem>?) {
        val adapter1 = GroupAdapter<ViewHolder>().also {
            it.addAll(item!!)
        }
        recylerview_gallery.layoutManager = GridLayoutManager(this, 2)
        recylerview_gallery.adapter = adapter1
    }

    override fun onSuccess(data: String) {
        gallery_progress.hide()
        toast(data)
        bottomSheetBehavior?.state = BottomSheetBehavior.STATE_HIDDEN
        finish()
    }

    override fun onFailure(msg: String) {
        gallery_progress.hide()
        toast(msg)
    }


    fun List<Gallery.Response>.changeItem(): List<GalleryFolderItem> {
        return this.map {
            GalleryFolderItem(it)
        }
    }
}
