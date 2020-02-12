package com.app.schoolmanagementstudent.result

import androidx.lifecycle.ViewModel
import com.app.schoolmanagementstudent.network.Repository
import com.app.schoolmanagementstudent.response.Result
import com.app.schoolmanagementstudent.response.StudentList
import com.app.schoolmanagementstudent.response.UpcomingTestList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ResultViewmodel(val repository: Repository) : ViewModel() {
    var testListener: ResultListener? = null

    fun allTest(class_id: String) {
        testListener?.onStarted()
        CoroutineScope(Dispatchers.Main).launch {
            repository.allTest(class_id).enqueue(object : Callback<UpcomingTestList> {
                override fun onFailure(call: Call<UpcomingTestList>, t: Throwable) {
                    testListener?.onFailure(t.message!!)
                }

                override fun onResponse(
                    call: Call<UpcomingTestList>,
                    response: Response<UpcomingTestList>
                ) {
                    if (response.isSuccessful)
                        testListener?.onAllTestSuccess(response.body()!!)
                    else
                        testListener?.onFailure(
                            JSONObject(response.errorBody()?.string()).getString(
                                "message"
                            )
                        )
//                            Log.e("error",response.errorBody()?.string())
                }

            })
        }
    }

    fun getResult(
        test_id: String,
        roll_no: String
    ) {
        testListener?.onStarted()
        CoroutineScope(Dispatchers.Main).launch {
            repository.getResult(test_id, roll_no)
                .enqueue(object : Callback<Result> {
                    override fun onFailure(call: Call<Result>, t: Throwable) {
                        testListener?.onFailure(t.message!!)
                    }

                    override fun onResponse(
                        call: Call<Result>,
                        response: Response<Result>
                    ) {
                        if (response.isSuccessful)
                            testListener?.onSuccess(response.body()!!)
                        else
                            testListener?.onFailure(
                                JSONObject(response.errorBody()?.string()).getString(
                                    "response"
                                )
                            )
//                            Log.e("error",response.errorBody()?.string())
                    }

                })
        }
    }


    fun allstudent(class_name: String, section_name: String) {
        testListener?.onStarted()
        CoroutineScope(Dispatchers.Main).launch {
            repository.allStudent(class_name, section_name).enqueue(object : Callback<StudentList> {
                override fun onFailure(call: Call<StudentList>, t: Throwable) {
                    testListener?.onFailure(t.message!!)
                }

                override fun onResponse(
                    call: Call<StudentList>,
                    response: Response<StudentList>
                ) {
                    if (response.isSuccessful)
                        testListener?.onAllStudentSuccess(response.body()!!)
                    else
                        testListener?.onFailure(
                            JSONObject(response.errorBody()?.string()).getString(
                                "message"
                            )
                        )
//                            Log.e("error",response.errorBody()?.string())
                }

            })
        }
    }

}