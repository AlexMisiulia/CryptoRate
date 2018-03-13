package best.lang.cryptorates.crypto

import best.lang.cryptorates.network.HttpClient
import javax.inject.Inject

open class CryptoRepository @Inject constructor(private val httpClient: HttpClient) {

    open suspend fun readCryptoRates()  = httpClient.readCryptoRates()

}