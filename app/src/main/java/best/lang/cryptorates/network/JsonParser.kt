package best.lang.cryptorates.network

import best.lang.cryptorates.entity.CryptoCurrency
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class JsonParser(private val gson: Gson) {

    fun getCryptoRates(response: String): Collection<CryptoCurrency> {
        val type = object : TypeToken<List<CryptoCurrency>>() {}.type
        return gson.fromJson(response, type)
    }

}