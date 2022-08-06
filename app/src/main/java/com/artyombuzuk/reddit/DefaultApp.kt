package com.artyombuzuk.reddit

import android.app.Application
import com.artyombuzuk.reddit.di.createKoinConfiguration
import org.koin.core.component.KoinComponent


class DefaultApp : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        startDependencyInjection()
    }

    private fun startDependencyInjection() {
        createKoinConfiguration(context = this)
    }
}
