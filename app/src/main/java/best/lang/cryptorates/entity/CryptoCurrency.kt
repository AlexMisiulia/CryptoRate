package best.lang.cryptorates.entity

import com.google.gson.annotations.SerializedName

const val IMAGE_BASE_URL = "https://res.cloudinary.com/dxi90ksom/image/upload/"

data class CryptoCurrency(
        val id: String,
        val name: String,
        val symbol: String,
        @SerializedName("price_usd")
        val priceUsd: String
) {
    // api is awesome, need to do it manually
    fun getImageUrl() : String {
        return "$IMAGE_BASE_URL$symbol.png"
    }
}