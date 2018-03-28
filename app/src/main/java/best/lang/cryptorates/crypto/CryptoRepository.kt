package best.lang.cryptorates.crypto

import best.lang.cryptorates.network.WebService
import javax.inject.Inject

open class CryptoRepository @Inject constructor(private val webService: WebService) {

    open suspend fun readCryptoRates(offset: Int, limit: Int = 50)  = webService.readCurrencies(offset, limit)

}