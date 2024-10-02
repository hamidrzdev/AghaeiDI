package ir.ham.aghaeidi.old

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App:Application() {
    private val TAG = App::class.simpleName

    override fun onCreate() {
        super.onCreate()
    }
}
