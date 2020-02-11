package com.app.schoolmanagementstudent.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.app.schoolmanagementstudent.HomeActivity
import com.app.schoolmanagementstudent.R
import com.app.schoolmanagementstudent.databinding.ActivityLoginBinding
import com.app.schoolmanagementstudent.response.TeacherLogin
import com.app.schoolmanagementstudent.utils.hide
import com.app.schoolmanagementstudent.utils.show
import com.app.schoolmanagementstudent.utils.toast
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.util.*

class LoginActivity : AppCompatActivity(),KodeinAware,LoginListener {

    override val kodein by kodein()
    lateinit var viewModel: LoginViewmodel
    lateinit var sharedPref: SharedPreferences
    var rotation: Float = 0.00f
    val factory: LoginViewmodelFactory by instance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val databind: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        sharedPref = getSharedPreferences("app", Context.MODE_PRIVATE)
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                logo.rotation = rotation
                rotation += 10
            }

        }, 100, 100)

        viewModel = ViewModelProviders.of(this, factory).get(LoginViewmodel::class.java)
        databind.data = viewModel
//        viewModel. = intent.getStringExtra("school_id")
        viewModel.loginListener = this
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(teacher: TeacherLogin.Response) {
        progress_bar.hide()

        sharedPref.edit().also {
            it.putBoolean("islogin", true)
            if(teacher.isIncharge=="1")
            it.putString("role", "incharge")
            else
                it.putString("role", "teacher")

            it.putString("id", teacher.id)
            it.putString("school_id", "")
            it.putString("class_name", teacher.className)
            it.putString("section_name", teacher.sectionName)
            it.putString("password", teacher.password)
            it.commit()
        }
        Intent(this, HomeActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK

            finish()
            startActivity(it)
        }
//        toast(student.name!!)
    }

    override fun onFailure(msg: String) {
        progress_bar.hide()
        toast(msg)
    }
}

