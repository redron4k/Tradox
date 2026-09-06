package redron.tradox

import android.app.Application
import redron.tradox.di.AppComponent
import redron.tradox.di.DaggerAppComponent


class TradoxApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}
