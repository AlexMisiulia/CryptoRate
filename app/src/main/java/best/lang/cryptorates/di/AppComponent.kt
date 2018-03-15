package best.lang.cryptorates.di

import best.lang.cryptorates.crypto.CryptoActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])

interface  AppComponent {

    fun inject(cryptoActivity: CryptoActivity)

}