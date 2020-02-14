package com.app.schoolmanagementstudent.gallery

import androidx.lifecycle.ViewModel
import com.app.schoolmanagementstudent.network.Repository
import com.app.schoolmanagementstudent.response.Gallery
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GalleryViewModel(val repository: Repository) : ViewModel() {
    var listener: GalleryListener? = null
    fun addNewFolder(foldername: String) {
        CoroutineScope(Dispatchers.Main).launch {
            //            listener?.onStarted()
//            repository.addFolder(foldername).enqueue(object : Callback<ResponseBody> {
//                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                    listener?.onFailure(t.message!!)
//                }
//
//                override fun onResponse(
//                    call: Call<ResponseBody>,
//                    response: Response<ResponseBody>
//                ) {
//                    if (response.isSuccessful) {
//                        listener?.onSuccess(JSONObject(response.body()?.string()).getString("message"))
//                    } else {
//                        listener?.onFailure(JSONObject(response.errorBody()?.string()).getString("message"))
//
//                    }
//                }
//
//            })
        }
    }

    fun getAllFolder() {
        listener?.onStarted()
        CoroutineScope(Dispatchers.IO).launch {
            repository.allFolder().enqueue(object : Callback<Gallery> {
                override fun onFailure(call: Call<Gallery>, t: Throwable) {
                    listener?.onFailure(t.message!!)
                }

                override fun onResponse(
                    call: Call<Gallery>,
                    response: Response<Gallery>
                ) {
                    listener?.onAllgallerySuccess(response.body()!!)
                }

            })

        }
    }

    fun getAllFiles(foldername: String) {
        listener?.onStarted()
        CoroutineScope(Dispatchers.IO).launch {
            repository.allFiles(foldername).enqueue(object : Callback<Gallery> {
                override fun onFailure(call: Call<Gallery>, t: Throwable) {
                    listener?.onFailure(t.message!!)
                }

                override fun onResponse(
                    call: Call<Gallery>,
                    response: Response<Gallery>
                ) {
                    listener?.onAllgallerySuccess(response.body()!!)
                }

            })

        }
    }

    fun upload(
        foldername: RequestBody,
        img: MultipartBody.Part
    ) {
        listener?.onStarted()
//        CoroutineScope(Dispatchers.IO).launch {
//            repository.uploadFiles(foldername, img).enqueue(object : Callback<Homework> {
//                override fun onFailure(call: Call<Homework>, t: Throwable) {
//                    listener?.onFailure(t.message!!)
//                }
//
//                override fun onResponse(
//                    call: Call<Homework>,
//                    response: Response<Homework>
//                ) {
//                    if (response.isSuccessful)
//                        listener?.onSuccess(response.body()?.response!!)
//                    else
//                        listener?.onFailure(JSONObject(response?.errorBody()?.string()).getString("message"))
//                }
//
//            })
//
//        }
    }
}