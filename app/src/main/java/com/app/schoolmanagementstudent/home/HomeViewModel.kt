package com.app.schoolmanagementstudent.home

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.app.schoolmanagement.utils.ApiException
import com.app.schoolmanagement.utils.NoInternetException
import com.app.schoolmanagementstudent.R
import com.app.schoolmanagementstudent.WebviewActivity
import com.app.schoolmanagementstudent.attendance.AttendenceActivity
import com.app.schoolmanagementstudent.businfo.BusInfoActivity
import com.app.schoolmanagementstudent.complaint.ComplaintActivity
import com.app.schoolmanagementstudent.event.EventActivity
import com.app.schoolmanagementstudent.feeinfo.FeeInfoActivity
import com.app.schoolmanagementstudent.gallery.GalleryActivity
import com.app.schoolmanagementstudent.homework.HomeworkActivity
import com.app.schoolmanagementstudent.leave.LeaveActivity
import com.app.schoolmanagementstudent.network.Repository
import com.app.schoolmanagementstudent.network.StudentRepository
import com.app.schoolmanagementstudent.notice.NoticeActivity
import com.app.schoolmanagementstudent.response.Classes
import com.app.schoolmanagementstudent.result.ResultActivity
import com.app.schoolmanagementstudent.timetable.TimeTableActivity
import com.app.schoolmanagementstudent.upcomingtest.UpcomingTest
import com.app.schoolmanagementstudent.videos.VideosActivity
import kotlinx.android.synthetic.main.dialog_edit_profile.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call


class HomeViewModel(val adminRepository: Repository, val studentRepository: StudentRepository) :
    ViewModel() {
    var class_name: String? = null
    var section_name: String? = null
    var total_student: String? = null

    var name: String? = null
    var mobile: String? = null
    var password: String? = null
    var student_id: String? = null
    var view1: Activity? = null
    var school_id: String? = null
    var homeFragmentListener: HomeFragmentListener? = null
    var dialog: AlertDialog? = null
    var class_list: Classes? = null
    var section_list: Classes? = null

    fun onedit_profile(view: View) {
        val sharedPreferences =
            view1?.getSharedPreferences("app", 0)
        val viewGroup: ViewGroup = view1?.findViewById(android.R.id.content)!!
        var dialogView = LayoutInflater.from(view.context)
            .inflate(R.layout.dialog_edit_profile, viewGroup, false)
        dialogView.name.setText(sharedPreferences?.getString("name", "")!!)
        dialogView.mobile_no.setText(sharedPreferences.getString("mobile", "")!!)
        dialogView.password.setText(sharedPreferences.getString("password", "")!!)
        dialogView.buttonOk.setOnClickListener {
            if (dialogView.name.toString().length == 0 || dialogView.mobile_no.toString()
                    .length == 0 || dialogView.password.toString().length == 0
            )
                Toast.makeText(view1, "All fields are mandatory", Toast.LENGTH_SHORT).show()
            else {
                CoroutineScope(Dispatchers.Main).launch {
                    try {

                        studentRepository.edit_profile(
                            dialogView.name.text.toString(),
                            dialogView.mobile_no.text.toString(),
                            dialogView.password.text.toString(),
                            student_id!!
                        )
                            .enqueue(object : retrofit2.Callback<ResponseBody> {
                                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                                }

                                override fun onResponse(
                                    call: Call<ResponseBody>,
                                    response: retrofit2.Response<ResponseBody>
                                ) {
                                    response.body().let {
                                        val data = it?.string()
                                        val jsonObject = JSONObject(data)
                                        if (jsonObject.getBoolean("success")) {
                                            Toast.makeText(
                                                view1,
                                                jsonObject.getString("message"),
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            homeFragmentListener?.onDataChanged(
                                                dialogView.name.text.toString()
                                            )

                                            sharedPreferences.edit().also {
                                                it?.putString(
                                                    "name",
                                                    dialogView.name.text.toString()
                                                )
                                                it?.putString(
                                                    "mobile",
                                                    dialogView.mobile_no.text.toString()
                                                )
                                                it?.putString(
                                                    "password",
                                                    dialogView.password.text.toString()
                                                )
                                                it?.commit()
                                            }
                                            dialog?.dismiss()
                                        } else
                                            Toast.makeText(
                                                view1,
                                                jsonObject.getString("message"),
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        dialog?.dismiss()
                                        return
                                    }
                                    Toast.makeText(
                                        view1,
                                        response.errorBody()?.string(),
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }

                            })
                    } catch (e: ApiException) {
                        homeFragmentListener?.onError(e.message!!)
                    } catch (e: NoInternetException) {
                        homeFragmentListener?.onError(e.message!!)
                    }
                }
            }
        }
        val alerdialog = AlertDialog.Builder(view1)
        alerdialog.setView(dialogView)
        dialog = alerdialog.create()
        dialog?.show()
    }

    fun onHomeWorkClick(view: View) {
        Intent(view.context, HomeworkActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onAttendanceClick(view: View) {
        Intent(view.context, AttendenceActivity::class.java).also {
            view.context.startActivity(it)
        }

    }

    fun onNoticeClick(view: View) {
        Intent(view.context, NoticeActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onUpcoingTestClick(view: View) {
        Intent(view.context, UpcomingTest::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onTimetableClick(view: View) {
        Intent(view.context, TimeTableActivity::class.java).also {
            view.context.startActivity(it)
        }
    }


    fun onBusInfoClick(view: View) {
        Intent(view.context, BusInfoActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onLeaveClick(view: View) {
        Intent(view.context, LeaveActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onFeeInfoClick(view: View) {
        Intent(view.context, FeeInfoActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onVideosClick(view: View) {
        Intent(view.context, VideosActivity::class.java).also {
            view.context.startActivity(it)
        }
    }


    fun onAboutUsClick(view: View) {
        Intent(view.context, WebviewActivity::class.java).also {
            it.putExtra("url", "https://www.ndpsedu.com/aboutus.aspx")
            view.context.startActivity(it)
        }
    }

    fun onContactUsClick(view: View) {
        Intent(view.context, WebviewActivity::class.java).also {
            it.putExtra("url", "https://www.ndpsedu.com/contactus.aspx")
            view.context.startActivity(it)
        }
    }

    fun onResultClick(view: View) {
        Intent(view.context, ResultActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onEventClick(view: View) {
        Intent(view.context, EventActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onComplaintClick(view: View) {
        Intent(view.context, ComplaintActivity::class.java).also {
            view.context.startActivity(it)
        }
    }


    fun savedToken(token: String, id: String) {

        CoroutineScope(Dispatchers.Main).launch {
            try {

                studentRepository.savedToken(
                    token,
                    id
                )
                    .enqueue(object : retrofit2.Callback<ResponseBody> {
                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                        }

                        override fun onResponse(
                            call: Call<ResponseBody>,
                            response: retrofit2.Response<ResponseBody>
                        ) {
                            if (response.isSuccessful) {
                                val json = JSONObject(response.body()?.string()!!)
//                                homeFragmentListener?.onError(json.getString("message"))
                            } else {
                                val json = JSONObject(response.errorBody()?.string()!!)

//                                homeFragmentListener?.onError(json.getString("message"))
                            }

                        }

                    })
            } catch (e: ApiException) {
                homeFragmentListener?.onError(e.message!!)
            } catch (e: NoInternetException) {
                homeFragmentListener?.onError(e.message!!)
            }
        }

    }

    fun onGalleryClick(view: View) {
        Intent(view.context, GalleryActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

}