package com.app.schoolmanagementstudent.login.studentsignup

import android.view.View
import androidx.lifecycle.ViewModel
import com.app.schoolmanagement.utils.ApiException
import com.app.schoolmanagement.utils.NoInternetException
import com.app.schoolmanagementstudent.login.studentlogin.StudentLoginListener
import com.app.schoolmanagementstudent.network.StudentSignupRepository
import com.app.schoolmanagementstudent.response.Classes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentSignupViewModel(val studentSignupRepository: StudentSignupRepository) : ViewModel() {
    var school_id: String? = null
    var class_name: String? = null
    var section_name: String? = null
    var roll_no: String? = null
    var password: String? = null
    var studentLoginListener: StudentLoginListener? = null
    var list: Classes? = null
    fun onLoginSignup(view: View) {
        studentLoginListener?.onStarted()
        if (school_id.isNullOrEmpty()) {
            studentLoginListener?.onFailure("School Id Not Empty.")
            return
        }

        if (class_name.isNullOrEmpty()) {
            studentLoginListener?.onFailure("Please enter class name.")
            return
        }

        if (section_name.isNullOrEmpty()) {
            studentLoginListener?.onFailure("Please enter section name.")
            return
        }
        if (roll_no.isNullOrEmpty()) {
            studentLoginListener?.onFailure("Please enter roll no.")
            return
        }
        if (password.isNullOrEmpty()) {
            studentLoginListener?.onFailure("Please enter password.")
            return
        }

        CoroutineScope(Dispatchers.Main).launch {
            try {


                val response = studentSignupRepository.getstudentLogin(
                    school_id!!,
                    class_name!!,
                    section_name!!,
                    roll_no!!,
                    password!!
                )

                response.response.let {
                    studentLoginListener?.onSuccess(it!!)
                    return@launch
                }
                studentLoginListener?.onFailure(response.message!!)
            } catch (e: ApiException) {
                studentLoginListener?.onFailure(e.message!!)
            } catch (e: NoInternetException) {
                studentLoginListener?.onFailure(e.message!!)
            }

        }
    }

    suspend fun getClasses() {
        CoroutineScope(Dispatchers.Main).launch {
            try {

                studentSignupRepository.getClasses(school_id!!).enqueue(object : Callback<Classes> {
                    override fun onFailure(call: Call<Classes>, t: Throwable) {
                        studentLoginListener?.onFailure(t.message!!)
                    }

                    override fun onResponse(call: Call<Classes>, response: Response<Classes>) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                studentLoginListener?.onClassSuccess(it)
                                list = it

                            }
                        }
                    }
                })
            } catch (e: ApiException) {
                studentLoginListener?.onFailure(e.message!!)
            } catch (e: NoInternetException) {
                studentLoginListener?.onFailure(e.message!!)
            }
        }

    }

    suspend fun getSection(class_name: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {

                studentSignupRepository.getSection(class_name).enqueue(object : Callback<Classes> {
                    override fun onFailure(call: Call<Classes>, t: Throwable) {
                        studentLoginListener?.onFailure(t.message!!)
                    }

                    override fun onResponse(call: Call<Classes>, response: Response<Classes>) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                studentLoginListener?.onSectionSuccess(it)
                                list = it

                            }
                        }
                    }
                })
            } catch (e: ApiException) {
                studentLoginListener?.onFailure(e.message!!)

            } catch (e: NoInternetException) {
                studentLoginListener?.onFailure(e.message!!)
            }
        }
    }
}