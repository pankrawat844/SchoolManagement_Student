package com.app.schoolmanagementstudent.login.studentlogin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.app.schoolmanagementstudent.HomeActivity
import com.app.schoolmanagementstudent.R
import com.app.schoolmanagementstudent.databinding.ActivityStudentLoginBinding
import com.app.schoolmanagementstudent.response.Classes
import com.app.schoolmanagementstudent.response.Student
import com.app.schoolmanagementstudent.utils.hide
import com.app.schoolmanagementstudent.utils.show
import com.app.schoolmanagementstudent.utils.toast

import kotlinx.android.synthetic.main.activity_student_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.util.*

class StudentLoginActivity : AppCompatActivity(), StudentLoginListener, KodeinAware {
    override val kodein by kodein()
    lateinit var viewModel: StudentLoginViewModel
    lateinit var sharedPref: SharedPreferences
    var rotation: Float = 0.00f
    val factory: StudentLoginViewModelFactory by instance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = getSharedPreferences("app", Context.MODE_PRIVATE)
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                logo.rotation = rotation
                rotation += 10
            }

        }, 100, 100)
        val databind: ActivityStudentLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_student_login)
        viewModel = ViewModelProviders.of(this, factory).get(StudentLoginViewModel::class.java)
        databind.data = viewModel
        viewModel.school_id = intent.getStringExtra("school_id")
        viewModel.studentLoginListener = this
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(student: Student.Response) {
        progress_bar.hide()

        sharedPref.edit().also {
            it.putBoolean("islogin", true)
            it.putString("role", "student")
            it.putString("name", student.name)
            it.putString("school_id", viewModel.school_id)
            it.putString("school_name", student.schoolName)
            it.putString("class_id", student.class_id)

            it.putString("roll_no", student.rollNo)
            it.putString("gender", student.gender)
            it.putString("address", student.address)
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

    override fun onClassSuccess(classes: Classes) {

    }

    override fun onSectionSuccess(classes: Classes) {

    }
}
