package best.lang.cryptorates

import android.app.Application
import best.lang.cryptorates.di.AppComponent
import best.lang.cryptorates.di.DaggerAppComponent

class CryptoApp : Application() {
    companion object {
        //platformStatic allow access it from java code
        @JvmStatic lateinit var graph: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        graph = DaggerAppComponent.create()
    }
}