package com.app.schoolmanagementstudent.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.app.schoolmanagementstudent.R
import com.app.schoolmanagementstudent.databinding.FragmentHomeBinding
import com.app.schoolmanagementstudent.utils.hide
import com.app.schoolmanagementstudent.utils.show
import com.app.schoolmanagementstudent.utils.toast
import kotlinx.android.synthetic.main.fragment_home.*

import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import java.util.*

class HomeFragment : Fragment(), KodeinAware, HomeFragmentListener {

    override val kodein by kodein()
    private val factoryAdmin: HomeViewModelFactory by instance()
    private lateinit var homeViewModel: HomeViewModel
    var rotation: Float = 0.00f
    private var sharedPreferences: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val databind: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val viewomodel = ViewModelProviders.of(this, factoryAdmin).get(HomeViewModel::class.java)
        databind.viewmodel = viewomodel
        databind.lifecycleOwner = this
        viewomodel.view1 = activity
        viewomodel.homeFragmentListener = this
        sharedPreferences = context?.getSharedPreferences("app", Context.MODE_PRIVATE)
        homeViewModel.name = sharedPreferences?.getString("name", "")
        homeViewModel.mobile = sharedPreferences?.getString("mobile", "")
        homeViewModel.password = sharedPreferences?.getString("password", "")
        homeViewModel.student_id = sharedPreferences?.getString("admin_id", "")
        homeViewModel.homeFragmentListener = this
        if (sharedPreferences?.contains("name")!!)
            databind.userName.text = "Welcome " + sharedPreferences?.getString("name", "")
        else
            databind.userName.text = "Welcome " + sharedPreferences?.getString("roll_no", "")

        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                databind.logo.rotation = rotation
                rotation += 10
            }

        }, 100, 100)
        return databind.root
    }

    override fun onDataChanged(name: String) {
        progress_bar.hide()
        activity?.toast(name)

    }

    override fun onError(msg: String) {
        progress_bar.hide()
        activity?.toast(msg)
    }

    override fun onStarted() {
        progress_bar.show()
    }


}
