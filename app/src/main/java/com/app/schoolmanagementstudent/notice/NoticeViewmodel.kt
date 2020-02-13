package com.app.schoolmanagementstudent.notice

import android.util.Log
import androidx.lifecycle.ViewModel
import com.app.schoolmanagementstudent.network.Repository
import com.app.schoolmanagementstudent.response.Homework
import com.app.schoolmanagementstudent.response.NoticeList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//import com.github.dhaval2404.imagepicker.ImagePicker

class NoticeViewmodel(val repository: Repository):ViewModel() {
        var noticeListener:NoticeListener?=null

    suspend fun addNotice( incharge_id:String,
                           title:String,
                           notice:String
                          )
    {
        noticeListener?.onStarted()
        repository.addNotice(incharge_id,title,notice).enqueue(object :Callback<Homework>{
            override fun onFailure(call: Call<Homework>, t: Throwable) {
                Log.e("homeviewmodel", "onFailure: " + t.message)
                noticeListener?.onFailure(t.message!!)

            }

            override fun onResponse(call: Call<Homework>, response: Response<Homework>) {
                if (response.isSuccessful) {
                    Log.e("homeviewmodel", "onsuccess: " + response.body()!!.response)
                    noticeListener?.onSuccess(response.body()!!)
                } else {
                    noticeListener?.onFailure(
                        JSONObject(
                            response.errorBody()?.string()!!
                        ).getString("response")
                    )
                }

            }

        })

    }

    fun allNotice( incharge_id: String){
        noticeListener?.onStarted()
        CoroutineScope(Dispatchers.Main).launch {
            repository.allNotice(incharge_id).enqueue(object:Callback<NoticeList>{
                override fun onFailure(call: Call<NoticeList>, t: Throwable) {
                noticeListener?.onFailure(t.message!!)
                }

                override fun onResponse(
                    call: Call<NoticeList>,
                    response: Response<NoticeList>
                ) {
                    if(response.isSuccessful)
                    noticeListener?.onAllNoticeSuccess(response.body()!!)
                    else
                        noticeListener?.onFailure(JSONObject(response.errorBody()?.string()).getString("message"))
//                            Log.e("error",response.errorBody()?.string())
                }

            })
        }
    }

}