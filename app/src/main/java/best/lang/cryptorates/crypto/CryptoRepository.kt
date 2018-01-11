package best.lang.cryptorates.crypto

import best.lang.cryptorates.network.HttpClient

class CryptoRepository(private val httpClient: HttpClient) {

    fun readCryptoRates()  = httpClient.readCryptoRates()
}