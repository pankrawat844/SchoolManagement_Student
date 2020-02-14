package com.app.schoolmanagementstudent.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.app.schoolmanagementstudent.R
import com.app.schoolmanagementstudent.response.Gallery
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso


class DataBinder {
    companion object {
        @BindingAdapter("src1")
        @JvmStatic
        fun setImageUrl(imageView: ImageView, url: String?) {
            Glide.with(imageView.context).load(url).into(imageView)
        }

        @JvmStatic
        @BindingAdapter("Piccasso")
        fun getImage(imageView: ImageView, value: Gallery.Response) {
            Picasso.get().load(Constants.base_url + value.value).placeholder(R.drawable.placeholder)
                .into(imageView)
        }
    }


}