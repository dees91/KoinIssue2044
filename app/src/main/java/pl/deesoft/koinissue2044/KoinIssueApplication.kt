package pl.deesoft.koinissue2044

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import pl.deesoft.koinissue2044.di.appModule

class KoinIssueApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@KoinIssueApplication)
            modules(appModule)
        }
    }
}
