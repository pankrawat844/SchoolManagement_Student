package com.app.schoolmanagementstudent

import android.app.Application
import com.app.schoolmanagementstudent.attendance.AttendenceViewmodel
import com.app.schoolmanagementstudent.attendance.AttendenceViewmodelFactory
import com.app.schoolmanagementstudent.businfo.BusInfoViewmodel
import com.app.schoolmanagementstudent.complaint.ComplaintViewmodel
import com.app.schoolmanagementstudent.complaint.ComplaintViewmodelFactory
import com.app.schoolmanagementstudent.event.EventViewmodel
import com.app.schoolmanagementstudent.event.EventViewmodelFactory
import com.app.schoolmanagementstudent.feeinfo.FeeInfoViewmodel
import com.app.schoolmanagementstudent.feeinfo.FeeInfoViewmodelFactory
import com.app.schoolmanagementstudent.home.HomeViewModel
import com.app.schoolmanagementstudent.home.HomeViewModelFactory
import com.app.schoolmanagementstudent.homework.HomeworkViewmodel
import com.app.schoolmanagementstudent.homework.HomeworkViewmodelFactory
import com.app.schoolmanagementstudent.leave.LeaveViewmodel
import com.app.schoolmanagementstudent.leave.LeaveViewmodelFactory
import com.app.schoolmanagementstudent.login.LoginViewmodel
import com.app.schoolmanagementstudent.login.LoginViewmodelFactory
import com.app.schoolmanagementstudent.login.schoollogin.SchoolLoginVIewModelFactory
import com.app.schoolmanagementstudent.login.schoollogin.SchoolLoginViewModel
import com.app.schoolmanagementstudent.login.studentlogin.StudentLoginViewModel
import com.app.schoolmanagementstudent.login.studentlogin.StudentLoginViewModelFactory
import com.app.schoolmanagementstudent.login.studentsignup.StudentSignupViewModel
import com.app.schoolmanagementstudent.login.studentsignup.StudentSignupViewModelFactory
import com.app.schoolmanagementstudent.network.*
import com.app.schoolmanagementstudent.notice.NoticeViewmodel
import com.app.schoolmanagementstudent.notice.NoticeViewmodelFactory
import com.app.schoolmanagementstudent.photopicker.loader.GlideImageLoader
import com.app.schoolmanagementstudent.result.ResultViewmodel
import com.app.schoolmanagementstudent.result.ResultmodelFactory
import com.app.schoolmanagementstudent.timetable.BusInfoViewmodelFactory
import com.app.schoolmanagementstudent.timetable.TimeTableViewmodel
import com.app.schoolmanagementstudent.timetable.TimeTableViewmodelFactory
import com.app.schoolmanagementstudent.upcomingtest.TestViewmodel
import com.app.schoolmanagementstudent.upcomingtest.TestViewmodelFactory
import com.app.schoolmanagementstudent.videos.VideosViewModel
import com.app.schoolmanagementstudent.videos.VideosViewModelFactory
import com.app.schoolmanagementstudent.videos.YoutubeDetailViewModel
import com.app.schoolmanagementstudent.videos.YoutubeDetailViewModelFactory
import lv.chi.photopicker.ChiliPhotoPicker
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class MainApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        bind() from singleton { MyApi() }
        bind() from singleton { Repository(instance()) }
        bind() from singleton { LoginViewmodelFactory(instance()) }
        bind() from singleton { LoginViewmodel(instance()) }

        bind() from singleton { HomeViewModelFactory(instance(), instance()) }
        bind() from singleton { HomeViewModel(instance(), instance()) }

        bind() from singleton { HomeworkViewmodel(instance()) }
        bind() from singleton { HomeworkViewmodelFactory(instance()) }

        bind() from singleton { NoticeViewmodel(instance()) }
        bind() from singleton { NoticeViewmodelFactory(instance()) }

        bind() from singleton { AttendenceViewmodelFactory(instance()) }
        bind() from singleton { AttendenceViewmodel(instance()) }

        bind() from singleton { TestViewmodelFactory(instance()) }
        bind() from singleton { TestViewmodel(instance()) }

        bind() from singleton { TimeTableViewmodel(instance()) }
        bind() from singleton { TimeTableViewmodelFactory(instance()) }

        bind() from singleton { BusInfoViewmodelFactory(instance()) }
        bind() from singleton { BusInfoViewmodel(instance()) }

        bind() from singleton { VideosViewModel(instance()) }
        bind() from singleton { VideosViewModelFactory(instance()) }
        bind() from singleton { VideoRepository(instance()) }
        bind() from singleton { YoutubeAPI() }
        bind() from singleton { YoutubeDetailViewModelFactory(instance()) }
        bind() from singleton { YoutubeDetailViewModel(instance()) }

        bind() from singleton { LeaveViewmodel(instance()) }
        bind() from singleton { LeaveViewmodelFactory(instance()) }

        bind() from singleton { FeeInfoViewmodel(instance()) }
        bind() from singleton { FeeInfoViewmodelFactory(instance()) }

        bind() from singleton { ResultViewmodel(instance()) }
        bind() from singleton { ResultmodelFactory(instance()) }

        bind() from singleton { EventViewmodel(instance()) }
        bind() from singleton { EventViewmodelFactory(instance()) }

        bind() from singleton { SchoolLoginRepository(instance()) }
        bind() from singleton { SchoolLoginVIewModelFactory(instance()) }
        bind() from singleton { SchoolLoginViewModel(instance()) }

        bind() from singleton { StudentRepository(instance()) }
        bind() from singleton { StudentLoginViewModel(instance()) }
        bind() from singleton { StudentLoginViewModelFactory(instance()) }

        bind() from singleton { StudentSignupRepository(instance()) }
        bind() from singleton { StudentSignupViewModel(instance()) }
        bind() from singleton { StudentSignupViewModelFactory(instance()) }

        bind() from singleton { ComplaintViewmodelFactory(instance()) }
        bind() from singleton { ComplaintViewmodel(instance()) }


    }

    override fun onCreate() {
        super.onCreate()
        ChiliPhotoPicker.init(
            loader = GlideImageLoader(),
            authority = "com.app.schoolmanagementteacher.fileprovider"
        )

    }
}