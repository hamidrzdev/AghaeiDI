package ir.ham.aghaeidi

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.Logger

class App:Application() {
    private val TAG = App::class.simpleName

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger(level = Level.DEBUG)
            printLogger()
            modules(appModule, viewModelModule)
        }
    }
}
