package best.lang.cryptorates.crypto.viewstate

import best.lang.cryptorates.entity.CryptoCurrency

data class CryptoViewState(
        val items: Collection<CryptoCurrency>,
        val errorInfo: ErrorInfo? = null,
        val isSwipeRefresh: Boolean = false,
        val isRecyclerLoading: Boolean = false
) {
    companion object {

        @JvmStatic
        fun toDataState(items: Collection<CryptoCurrency>) = CryptoViewState(items)

        @JvmStatic
        fun errorState(items: Collection<CryptoCurrency>, errorInfo: ErrorInfo?) = CryptoViewState(items, errorInfo)

        @JvmStatic
        fun listLoadingState(items: Collection<CryptoCurrency>, isLoading: Boolean) = CryptoViewState(items, isRecyclerLoading = isLoading)

        @JvmStatic
        fun swipeRefreshState(items: Collection<CryptoCurrency>, isRefreshing: Boolean) = CryptoViewState(items, isSwipeRefresh =
        isRefreshing)

    }
}