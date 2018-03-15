package best.lang.cryptorates

import best.lang.cryptorates.di.AppComponent
import best.lang.cryptorates.di.DaggerTestComponent
import best.lang.cryptorates.di.TestComponent

class TestApp : CryptoApp() {

    companion object {
        fun testGraph() = CryptoApp.graph as TestComponent
    }

    override fun createGraph(): AppComponent {
        return DaggerTestComponent.create()
    }
}