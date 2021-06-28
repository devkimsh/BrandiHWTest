package com.example.brandihwtest

import android.app.Activity
import android.app.Application
import com.example.brandihwtest.repository.RetrofitRepository
import com.example.brandihwtest.viewmodel.ImageListViewModel
import io.reactivex.Single
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class BrandiHWTestApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val viewModelModule = module {
            viewModel { ImageListViewModel() }
        }

        startKoin {
            androidContext(this@BrandiHWTestApp)
            modules(listOf(viewModelModule))
        }

    }
}