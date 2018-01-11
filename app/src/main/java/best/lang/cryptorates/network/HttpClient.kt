package best.lang.cryptorates.network

import android.util.Log
import best.lang.cryptorates.entity.CryptoCurrency
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject

class HttpClient @Inject constructor(private val jsonParser: JsonParser) {

    @Throws(IOException::class, JSONException::class)
    fun readCryptoRates(): Collection<CryptoCurrency> {
        val requestUrl = "https://api.coinmarketcap.com/v1/ticker/?limit=50"
        val response = getResponse(requestUrl)
        return jsonParser.getCryptoRates(response)
    }

    @Throws(IOException::class)
    private fun getResponse(requestUrl: String): String {
        val url = URL(requestUrl)
        val connection = url.openConnection() as HttpURLConnection

        connection.connect()

        val `in`: InputStream
        val status = connection.responseCode
        if (status != HttpURLConnection.HTTP_OK) {
            `in` = connection.errorStream
        } else {
            `in` = connection.inputStream
        }

        return convertStreamToString(`in`)
    }

    @Throws(IOException::class)
    private fun convertStreamToString(stream: InputStream): String {

        val reader = BufferedReader(InputStreamReader(stream))
        val sb = StringBuilder()

        while (true) {
            val line = reader.readLine() ?: break
            sb.append(line)
        }

        stream.close()

        return sb.toString()
    }
}