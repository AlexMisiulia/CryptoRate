package best.lang.cryptorates.di

import best.lang.cryptorates.crypto.CryptoFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])

interface  AppComponent {

    fun inject(cryptoFragment: CryptoFragment)

}