package com.app.schoolmanagementstudent.gallery

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toFile
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.schoolmanagementstudent.R
import com.app.schoolmanagementstudent.response.Gallery
import com.app.schoolmanagementstudent.utils.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_gallery_item.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lv.chi.photopicker.PhotoPickerFragment
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody

import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.io.File

class GalleryItemActivity : AppCompatActivity(), GalleryListener, KodeinAware,
    PhotoPickerFragment.Callback {
    var viewModel: GalleryViewModel? = null
    override val kodein by kodein()
    var list: List<Gallery.Response>? = null

    val factory: GalleryViewModelFactory by instance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_item)
        viewModel = ViewModelProviders.of(this, factory).get(GalleryViewModel::class.java)
        viewModel?.listener = this
        viewModel?.getAllFiles(intent.getStringExtra("folder_name"))
        menu.setOnClickListener {
            PhotoPickerFragment.newInstance(
                multiple = false,
                allowCamera = false,
                maxSelection = 5,
                theme = R.style.ChiliPhotoPicker_Dark
            ).show(supportFragmentManager, "time_table")
        }
        recylerview.addOnItemTouchListener(
            RecyclerItemClickListenr(
                this,
                recylerview,
                object : RecyclerItemClickListenr.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        Intent(this@GalleryItemActivity, FullScreenActivity::class.java).also {
                            it.putExtra("url", list?.get(position)?.value)
                            startActivity(it)
                        }
                    }

                    override fun onItemLongClick(view: View?, position: Int) {
                    }

                })
        )
    }

    override fun onStarted() {
        gallery_progress.show()
    }

    override fun onAllgallerySuccess(data: Gallery) {
        gallery_progress.hide()
        list = data.response!!
        initRecylerview(data.response.changeItem())
    }

    override fun onSuccess(data: String) {
        gallery_progress.hide()
        toast(data)
        finish()
    }

    override fun onFailure(msg: String) {
        gallery_progress.hide()
    }


    private fun initRecylerview(item: List<GalleryFilesItem>?) {
        val adapter1 = GroupAdapter<ViewHolder>().also {
            it.addAll(item!!)
        }
        recylerview.layoutManager = LinearLayoutManager(this)
        recylerview.adapter = adapter1
    }

    fun List<Gallery.Response>.changeItem(): List<GalleryFilesItem> {
        return this.map {
            GalleryFilesItem(it)
        }
    }

    override fun onImagesPicked(photos: ArrayList<Uri>) {
        var path: RequestBody? = null
        path = File(photos[0].path).asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val body: MultipartBody.Part =
            MultipartBody.Part.createFormData("image", photos[0].toFile().name, path)
        val folder_name: RequestBody =
            intent.getStringExtra("folder_name").toRequestBody("text/plain".toMediaTypeOrNull())
//        val section_name: RequestBody =
//            sharedPreferences.getString(
//                "section_name",
//                ""
//            )!!.toRequestBody("text/plain".toMediaTypeOrNull())
        CoroutineScope(Dispatchers.Main).launch {
            viewModel?.upload(folder_name, body)
        }
    }

    override fun onImagesPicked(photos: ArrayList<Uri>, data: Intent?) {

    }

}