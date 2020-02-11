package com.app.schoolmanagementstudent.videos

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.app.schoolmanagementstudent.R
import com.app.schoolmanagementstudent.databinding.VideodetailListBinding
import com.app.schoolmanagementstudent.response.YoutubeVideoRelated
import com.bumptech.glide.Glide

import com.xwray.groupie.databinding.BindableItem
import java.io.Serializable

class YoutubeDetailList(val data: YoutubeVideoRelated.Item) :
    BindableItem<VideodetailListBinding>(), Serializable {

    override fun getLayout(): Int {
        return R.layout.videodetail_list
    }

    override fun bind(viewBinding: VideodetailListBinding, position: Int) {
        viewBinding.viewmodel = data
    }


    @BindingAdapter("app:profileImage")
    fun setImageUrl(imageView: ImageView, url: String) {
        val context = imageView.context
        Glide.with(context).load(url).into(imageView)
    }


}