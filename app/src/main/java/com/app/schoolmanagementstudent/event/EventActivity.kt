package com.app.schoolmanagementstudent.event

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.schoolmanagementstudent.R
import com.app.schoolmanagementstudent.databinding.ActivityEventBinding
import com.app.schoolmanagementstudent.response.Homework
import com.app.schoolmanagementstudent.response.NoticeList
import com.app.schoolmanagementstudent.utils.RecyclerItemClickListenr
import com.app.schoolmanagementstudent.utils.hide
import com.app.schoolmanagementstudent.utils.show
import com.app.schoolmanagementstudent.utils.toast
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_event.*
import kotlinx.android.synthetic.main.activity_notice.progress_bar
import kotlinx.android.synthetic.main.bottomsheet_event.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class EventActivity : AppCompatActivity(), KodeinAware, EventListener {
    override val kodein by kodein()
    val factory: EventViewmodelFactory by instance()
    var sharedPreferences: SharedPreferences? = null
    var list = ArrayList<NoticeList.Response>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val databinding =
            DataBindingUtil.setContentView<ActivityEventBinding>(this, R.layout.activity_event)
        val viewmodel = ViewModelProviders.of(this, factory).get(EventViewmodel::class.java)
        sharedPreferences = getSharedPreferences("app", Context.MODE_PRIVATE)
        viewmodel.noticeListener = this
        databinding.viewmodel = viewmodel
        viewmodel.allNotice(sharedPreferences?.getString("class_id", "")!!)
        val bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet_event)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        bottom_sheet_nxt.setOnClickListener {

            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED)
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }


        event_recyclerview.addOnItemTouchListener(
            RecyclerItemClickListenr(
                this,
                event_recyclerview,
                object : RecyclerItemClickListenr.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        titl.text = list[position].title
                        date1.text = list[position].date
                        event_txt.text = list[position].notice

                        if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_HIDDEN)
                            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                    }

                    override fun onItemLongClick(view: View?, position: Int) {

                    }
                })
        )


    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(data: Homework) {
        toast(data.response!!)
        finish()
    }

    override fun onAllNoticeSuccess(data: NoticeList) {
        progress_bar.hide()
        list.addAll(data.response!!)
        Log.e("TAG", "onAllNoticeSuccess: " + data.response)
        initRecyerview(data.response.toNoticeItem())
    }

    private fun initRecyerview(toNoticeItem: List<EventItem>) {
        val adapter = GroupAdapter<ViewHolder>().apply {
            addAll(toNoticeItem)
        }
        event_recyclerview.apply {
            layoutManager = LinearLayoutManager(this@EventActivity)
            setAdapter(adapter)


        }

    }

    override fun onFailure(msg: String) {
        progress_bar.hide()
        toast(msg)
    }


    private fun List<NoticeList.Response>.toNoticeItem(): List<EventItem> {
        return this.map {
            EventItem(it)
        }
    }
}