package best.lang.cryptorates.di

import android.util.Log
import best.lang.cryptorates.network.WebService
import dagger.Component
import dagger.Module
import dagger.Provides
import org.mockito.Mockito.mock
import javax.inject.Singleton

@Singleton
@Component(modules = [TestModule::class, AppModule::class])
interface TestComponent : AppComponent {
    fun mockWebService(): WebService
}

@Module
class TestModule {

    @Singleton
    @Provides
    fun providesMockWebServer(): WebService {
        return mock(WebService::class.java)
    }
}