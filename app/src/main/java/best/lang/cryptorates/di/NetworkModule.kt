package best.lang.cryptorates.di

import best.lang.cryptorates.network.WebService
import com.jaredsburrows.retrofit2.adapter.synchronous.SynchronousCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@Singleton
class NetworkModule {

    @Singleton
    @Provides
    fun providesWebService() : WebService {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.coinmarketcap.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(SynchronousCallAdapterFactory.create())
                .build()

        return retrofit.create<WebService>(WebService::class.java)
    }

}