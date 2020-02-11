package com.app.schoolmanagementstudent.login.studentlogin

import android.view.View
import androidx.lifecycle.ViewModel
import com.app.schoolmanagement.utils.ApiException
import com.app.schoolmanagement.utils.NoInternetException
import com.app.schoolmanagementstudent.network.StudentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentLoginViewModel(val studentRepository: StudentRepository) : ViewModel() {
    var school_id: String? = null
    var roll_no: String? = null
    var password: String? = null
    var studentLoginListener: StudentLoginListener? = null
    fun onLoginclick(view: View) {
        studentLoginListener?.onStarted()
        if (school_id.isNullOrEmpty()) {
            studentLoginListener?.onFailure("School id is Missing.")
            return
        }
        if (roll_no.isNullOrEmpty()) {
            studentLoginListener?.onFailure("Roll No is Missing.")
            return
        }
        if (password.isNullOrEmpty()) {
            studentLoginListener?.onFailure("Password is Missing.")
            return
        }
        CoroutineScope(Dispatchers.Main).launch {
            try {

                val response =
                    studentRepository.getStudentDetails(school_id!!, roll_no!!, password!!)
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


}