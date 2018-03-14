package best.lang.cryptorates.di

import best.lang.cryptorates.crypto.CryptoActivity
import best.lang.cryptorates.crypto.CryptoRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])

interface  AppComponent {

    fun inject(cryptoActivity: CryptoActivity)
    fun cryptoRepository(): CryptoRepository

}