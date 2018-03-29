package best.lang.cryptorates.crypto

import best.lang.cryptorates.entity.CryptoCurrency
import best.lang.cryptorates.network.WebService
import java.util.*
import javax.inject.Inject

open class CryptoRepository @Inject constructor(private val webService: WebService) {
    private val random = Random()

    open suspend fun readCryptoRates(offset: Int, limit: Int = 50) : Collection<CryptoCurrency>  {
        if(random.nextInt() % 3 == 0) throw RuntimeException()
        return webService.readCurrencies(offset, limit)
    }

}