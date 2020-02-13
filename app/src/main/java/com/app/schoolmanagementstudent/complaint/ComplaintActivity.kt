package com.app.schoolmanagementstudent.complaint

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.schoolmanagementstudent.R
import com.app.schoolmanagementstudent.databinding.ActivityComplaintBinding
import com.app.schoolmanagementstudent.response.ComplaintList
import com.app.schoolmanagementstudent.response.Homework
import com.app.schoolmanagementstudent.utils.hide
import com.app.schoolmanagementstudent.utils.show
import com.app.schoolmanagementstudent.utils.toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_complaint.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ComplaintActivity : AppCompatActivity(), KodeinAware, ComplaintListener {
    var sharedPreferences: SharedPreferences? = null
    override val kodein by kodein()
    val factory: ComplaintViewmodelFactory by instance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val databinding = DataBindingUtil.setContentView<ActivityComplaintBinding>(
            this,
            R.layout.activity_complaint
        )
        val viewmodel = ViewModelProviders.of(this, factory).get(ComplaintViewmodel::class.java)
        sharedPreferences = getSharedPreferences("app", Context.MODE_PRIVATE)
        viewmodel.complaintListener = this
        databinding.viewmodel = viewmodel
        viewmodel.allComplaint(sharedPreferences?.getString("student_id", "")!!)
//        val bottomSheetBehavior= BottomSheetBehavior.from(bottom_sheet_notice)
//        bottomSheetBehavior.state= BottomSheetBehavior.STATE_HIDDEN
//        menu.setOnClickListener {
//            if(bottomSheetBehavior.state== BottomSheetBehavior.STATE_HIDDEN)
//                bottomSheetBehavior.state= BottomSheetBehavior.STATE_EXPANDED
//        }
//        bottom_sheet_nxt.setOnClickListener {
//            val title=titl.text.toString().trim()
//            val notice=notice_txt.text.toString().trim()
//            CoroutineScope(Dispatchers.Main).launch {
//                if(title.isNullOrBlank() || notice.isNullOrBlank())
//                    toast("Both field is mandatory.")
//                else
//                    viewmodel.addComplaint(sharedPreferences?.getString("student_id", "")!!, sharedPreferences?.getString("class_id", "")!!,title,notice)
//            }
//        }

    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(data: Homework) {
        toast(data.response!!)
        finish()
    }

    override fun onAllNoticeSuccess(data: ComplaintList) {
        progress_bar.hide()
        Log.e("TAG", "onAllNoticeSuccess: " + data.response)
        initRecyerview(data.response?.toNoticeItem()!!)
    }

    private fun initRecyerview(toNoticeItem: List<ComplaintItem>) {
        val adapter = GroupAdapter<ViewHolder>().apply {
            addAll(toNoticeItem)
        }
        notice_recyclerview.apply {
            layoutManager = LinearLayoutManager(this@ComplaintActivity)
            setAdapter(adapter)


        }

    }

    override fun onFailure(msg: String) {
        progress_bar.hide()
        toast(msg)
    }


    private fun List<ComplaintList.Response>.toNoticeItem(): List<ComplaintItem> {
        return this.map {
            ComplaintItem(it)
        }
    }
}