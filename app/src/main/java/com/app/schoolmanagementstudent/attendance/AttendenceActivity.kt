package com.app.schoolmanagementstudent.attendance

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.app.schoolmanagementstudent.R
import com.app.schoolmanagementstudent.response.CheckAttendence
import com.app.schoolmanagementstudent.response.Homework
import com.app.schoolmanagementstudent.response.StudentList
import com.app.schoolmanagementstudent.utils.toast
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import kotlinx.android.synthetic.main.activity_attendence.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class AttendenceActivity : AppCompatActivity(), KodeinAware, AttendenceListener {
    override val kodein by kodein()
    private var mShortMonths: Array<String>? = null
    private var sharedPreferences: SharedPreferences? = null
    val factory: AttendenceViewmodelFactory by instance()
    private var events: ArrayList<EventDay> = ArrayList<EventDay>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendence)
        sharedPreferences = getSharedPreferences("app", Context.MODE_PRIVATE)
        val viewmodel = ViewModelProviders.of(this, factory).get(AttendenceViewmodel::class.java)
        viewmodel.attedenceListener = this
        CoroutineScope(Dispatchers.IO).launch {
            viewmodel.check_attendence(sharedPreferences?.getString("class_id", "")!!)
        }
        calenderview.setOnDayClickListener(object : OnDayClickListener {
            @SuppressLint("SimpleDateFormat")
            override fun onDayClick(eventDay: EventDay) {
//               toast(eventDay.calendar.time.toString())
//           Intent(this@AttendenceActivity,StudentListActivity::class.java).also {
                val date = Calendar.getInstance()
                val dateFormat = SimpleDateFormat("dd/MM/yyyy")

                date.time = eventDay.calendar.time
                toast(dateFormat.format(eventDay.calendar.time))
//               it.putExtra(
//                   "date",
//                   "" + date.get(Calendar.DATE) + "/" + date.get(Calendar.MONTH).plus(1 )+ "/" + date.get(
//                       Calendar.YEAR
//                   )
//               )
//               startActivity(it)
//           }

            }

        })


    }

    override fun onStarted() {

    }

    override fun onSuccess(data: Homework) {
    }

    override fun onAllStudentSuccess(data: StudentList) {
    }

    @SuppressLint("SimpleDateFormat")
    override fun onCheckAttendence(data: CheckAttendence) {
//        Log.e("attendence",data.toString())

        for (i in data.response) {
            Log.e("check attendence", i.toString())
            for (j in i.attendence.asIterable()) {
                if (j.rollNo == sharedPreferences?.getString("roll_no", "")) {
                    if (j.attendence == "p") {
                        val date = Calendar.getInstance()
                        val dateFormat = SimpleDateFormat("dd/MM/yyyy")

                        date.time = dateFormat.parse(i.date)!!
                        events.add(EventDay(date, R.drawable.p_tick, Color.parseColor("#ffffff")))
//                        calender.time=
                    }

                    if (j.attendence == "a") {
                        val date = Calendar.getInstance()
                        val dateFormat = SimpleDateFormat("dd/MM/yyyy")

                        date.time = dateFormat.parse(i.date)!!
                        events.add(EventDay(date, R.drawable.a_cross, Color.parseColor("#ffffff")))
//                        calender.time=
                    }
                }
            }
        }

        calenderview.setEvents(events)

    }

    override fun onCheckAttendenceFailour(msg: String) {
    }

    override fun onFailure(msg: String) {
    }


}