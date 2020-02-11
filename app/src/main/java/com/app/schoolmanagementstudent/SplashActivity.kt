package com.app.schoolmanagementstudent

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.app.schoolmanagementstudent.login.schoollogin.SchoolLoginActivity

class SplashActivity : AppCompatActivity() {
    lateinit var sharedPreferences:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        sharedPreferences = getSharedPreferences("app", Context.MODE_PRIVATE)
        val handler=Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (sharedPreferences.getBoolean("islogin", false)) {

                        Intent(this@SplashActivity, HomeActivity::class.java).apply {
                            startActivity(this)
                            finish()
                        }

                } else {
                    Intent(this@SplashActivity, SchoolLoginActivity::class.java).apply {
                        startActivity(this)
                        finish()
                    }
                }
            }

        },3000)

    }
}
