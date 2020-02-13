package com.app.schoolmanagementstudent.complaint

import android.util.Log
import androidx.lifecycle.ViewModel
import com.app.schoolmanagementstudent.network.Repository
import com.app.schoolmanagementstudent.response.ComplaintList
import com.app.schoolmanagementstudent.response.Homework
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ComplaintViewmodel(val repository: Repository):ViewModel() {
    var complaintListener: ComplaintListener? = null

    suspend fun addComplaint(
        incharge_id: String,
        class_id: String,
        title: String,
        notice: String
    ) {
        complaintListener?.onStarted()
        repository.addComplaint(incharge_id, class_id, title, notice)
            .enqueue(object : Callback<Homework> {
                override fun onFailure(call: Call<Homework>, t: Throwable) {
                    Log.e("homeviewmodel", "onFailure: " + t.message)
                    complaintListener?.onFailure(t.message!!)

                }

                override fun onResponse(call: Call<Homework>, response: Response<Homework>) {
                    Log.e("homeviewmodel", "onsuccess: " + response.body()!!.response)
                    complaintListener?.onSuccess(response.body()!!)

            }

        })

    }

    fun allComplaint(student_id: String) {
        complaintListener?.onStarted()
        CoroutineScope(Dispatchers.Main).launch {
            repository.allComplaint(student_id).enqueue(object : Callback<ComplaintList> {
                override fun onFailure(call: Call<ComplaintList>, t: Throwable) {
                    complaintListener?.onFailure(t.message!!)
                }

                override fun onResponse(
                    call: Call<ComplaintList>,
                    response: Response<ComplaintList>
                ) {
                    if (response.isSuccessful)
                        complaintListener?.onAllNoticeSuccess(response.body()!!)
                    else
                        complaintListener?.onFailure(JSONObject(response.errorBody()?.string()).getString("message"))
//                            Log.e("error",response.errorBody()?.string())
                }

            })
        }
    }

}