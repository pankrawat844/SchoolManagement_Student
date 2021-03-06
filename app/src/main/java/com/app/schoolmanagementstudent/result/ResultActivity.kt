package com.app.schoolmanagementstudent.result

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
import com.app.schoolmanagementstudent.databinding.ActivityResultBinding
import com.app.schoolmanagementstudent.response.Result
import com.app.schoolmanagementstudent.response.StudentList
import com.app.schoolmanagementstudent.response.UpcomingTestList
import com.app.schoolmanagementstudent.upcomingtest.UpcoingTestItem
import com.app.schoolmanagementstudent.utils.RecyclerItemClickListenr
import com.app.schoolmanagementstudent.utils.hide
import com.app.schoolmanagementstudent.utils.show
import com.app.schoolmanagementstudent.utils.toast
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.bottomsheet_result.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ResultActivity : AppCompatActivity(), KodeinAware, ResultListener {
    override val kodein by kodein()
    val factory: ResultmodelFactory by instance()
    var sharedPreferences: SharedPreferences? = null
    lateinit var list: UpcomingTestList
    var isupdateing: Boolean = false
    var viewmodel: ResultViewmodel? = null
    lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val databinding = DataBindingUtil.setContentView<ActivityResultBinding>(
            this,
            R.layout.activity_result
        )
        viewmodel = ViewModelProviders.of(this, factory).get(ResultViewmodel::class.java)
        sharedPreferences = getSharedPreferences("app", Context.MODE_PRIVATE)
        viewmodel?.testListener = this
        databinding.viewmodel = viewmodel
        viewmodel?.allTest(sharedPreferences?.getString("class_id", "")!!)
        bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet_result)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN


        bottom_sheet_nxt.setOnClickListener {
            //            val roll_no = roll_no.selectedItem.toString()
            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            }
            date1.text = ""
            roll_no.text = ""
            max_marks.text = ""
            marks_obtained.text = ""
        }

        text_recyclerview.addOnItemTouchListener(
            RecyclerItemClickListenr(
                this,
                text_recyclerview,
                object : RecyclerItemClickListenr.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {

                        if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                        }


                        viewmodel?.getResult(
                            list.response?.get(position)?.id!!,
                            sharedPreferences?.getString("roll_no", "")!!
                        )

                    }

                    override fun onItemLongClick(view: View?, position: Int) {

                    }

                })
        )

    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(data: Result) {
//        name.setText(data.response?.testId!!)
        date1.text = data.response?.date
        roll_no.text = data.response?.rollNo!!
        max_marks.text = data.response.maxMark!!
        marks_obtained.text = data.response.markObtain
        toast(data.message!!)
        if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_HIDDEN) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    override fun onAllStudentSuccess(data: StudentList) {
        progress_bar.hide()
//        roll_no.adapter = data.response?.toItem()?.let {
//            ArrayAdapter(
//                this, android.R.layout.simple_dropdown_item_1line,
//                it
//            )
//        }
    }

    private fun List<StudentList.Response>.toItem(): List<String> {
        return this.map {
            it.rollNo!!
        }
    }

    override fun onAllTestSuccess(data: UpcomingTestList) {
        progress_bar.hide()
        list = data
        Log.e("TAG", "onAllNoticeSuccess: " + data.response)
        initRecyerview(data.response?.toNoticeItem()!!)
//        viewmodel?.allstudent(
//            sharedPreferences?.getString("class_name", "")!!,
//            sharedPreferences?.getString("section_name", "")!!
//        )
    }

    private fun initRecyerview(toNoticeItem: List<UpcoingTestItem>) {
        val adapter = GroupAdapter<ViewHolder>().apply {
            addAll(toNoticeItem)
        }
        text_recyclerview.apply {
            layoutManager = LinearLayoutManager(this@ResultActivity)
            setAdapter(adapter)


        }

    }

    override fun onFailure(msg: String) {
        progress_bar.hide()
        toast(msg)
    }


    private fun List<UpcomingTestList.Response>.toNoticeItem(): List<UpcoingTestItem> {
        return this.map {
            UpcoingTestItem(it)
        }
    }
}