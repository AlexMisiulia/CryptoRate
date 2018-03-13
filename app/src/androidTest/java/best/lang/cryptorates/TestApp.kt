package best.lang.cryptorates

import best.lang.cryptorates.di.AppComponent
import best.lang.cryptorates.di.DaggerTestComponent

class TestApp : CryptoApp() {

    companion object {
        fun graph() = CryptoApp.graph
    }

    override fun createGraph(): AppComponent {
        return DaggerTestComponent.create()
    }
}