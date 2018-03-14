package best.lang.cryptorates.network

import best.lang.cryptorates.entity.CryptoCurrency
import retrofit2.http.GET
import retrofit2.http.Query


interface WebService {
    @GET("ticker/")
    fun readCurrencies(@Query("limit") limit: Int): List<CryptoCurrency>
}