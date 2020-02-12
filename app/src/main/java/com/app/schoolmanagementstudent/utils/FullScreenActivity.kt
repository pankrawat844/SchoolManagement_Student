package com.app.schoolmanagementstudent.utils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.schoolmanagementstudent.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_full_screen.*

class FullScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen)
        Picasso.get().load(Constants.homework_url + intent.getStringExtra("url")).into(img)
    }
}