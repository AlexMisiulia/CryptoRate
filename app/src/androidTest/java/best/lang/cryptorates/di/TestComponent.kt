package best.lang.cryptorates.di

import android.util.Log
import best.lang.cryptorates.crypto.CryptoRepository
import dagger.Component
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import javax.inject.Singleton

@Singleton
@Component(modules = [TestModule::class, AppModule::class])
interface TestComponent : AppComponent

@Module
class TestModule {

    @Provides
    fun providesCryptoReposityory(): CryptoRepository {
        val cryptoRepository = Mockito.mock(CryptoRepository::class.java)
        Log.d("FuncTest","create mock with hash ${cryptoRepository.hashCode()}")
        return cryptoRepository
    }
}