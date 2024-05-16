package org.sopt.sopkathon.android3

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class SopkathonApp : Application() {
    override fun onCreate() {
        super.onCreate()

        disableDarkMode()
    }

    private fun disableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}