package best.lang.cryptorates.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@Singleton
class AppModule {

    @Singleton
    @Provides
    fun providesGson()  = Gson()

}