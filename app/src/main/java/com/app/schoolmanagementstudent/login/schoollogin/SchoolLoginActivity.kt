package com.app.schoolmanagementstudent.login.schoollogin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.app.schoolmanagementstudent.R
import com.app.schoolmanagementstudent.databinding.ActivitySchoolLoginBinding
import com.app.schoolmanagementstudent.login.studentsignup.StudentSignupActivity
import com.app.schoolmanagementstudent.response.SchoolLoginResponse
import com.app.schoolmanagementstudent.utils.hide
import com.app.schoolmanagementstudent.utils.show
import com.app.schoolmanagementstudent.utils.toast
import kotlinx.android.synthetic.main.activity_school_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.util.*

class SchoolLoginActivity : AppCompatActivity(),
    SchoolLoginListener, KodeinAware {
    override val kodein by kodein()
    lateinit var viewModel: SchoolLoginViewModel
    var rotation: Float = 0.00f
    private val factory: SchoolLoginVIewModelFactory by instance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySchoolLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_school_login)
        viewModel = ViewModelProviders.of(this, factory).get(SchoolLoginViewModel::class.java)
        viewModel.schoolLoginListener = this
        binding.data = viewModel
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                logo.rotation = rotation
                rotation += 10
            }

        }, 100, 100)

    }

    override fun onStarted() {
//    toast("Started")
        progress_bar.show()
    }

    override fun onFailure(msg: String) {
        toast(msg)
        progress_bar.hide()
    }

    override fun onSuccess(result: SchoolLoginResponse.Response?) {

        toast("School Login Successfully.")
        Intent(
            this@SchoolLoginActivity,
            StudentSignupActivity::class.java
        ).also {
            it.putExtra("school_id", result?.id.toString())
            finish()
            startActivity(it)
        }
        progress_bar.hide()
    }


}
