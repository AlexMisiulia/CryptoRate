package best.lang.cryptorates.runner

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner
import best.lang.cryptorates.TestApp

class CustomRunner : AndroidJUnitRunner() {

    @Throws(Exception::class)
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, TestApp::class.java.name, context)
    }

}