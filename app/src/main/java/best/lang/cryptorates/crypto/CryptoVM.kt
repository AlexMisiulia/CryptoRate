package best.lang.cryptorates.crypto

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import best.lang.cryptorates.R
import best.lang.cryptorates.crypto.viewstate.CryptoViewState
import best.lang.cryptorates.crypto.viewstate.ErrorInfo
import best.lang.cryptorates.utils.CoroutineContextProvider
import best.lang.cryptorates.utils.CoroutinesActions.Companion.withContext
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject


open class CryptoVM @Inject constructor(private val repository: CryptoRepository,
                                        private val contextProvider: CoroutineContextProvider) : ViewModel() {
    private val cryptoRatesLiveData = MutableLiveData<CryptoViewState>()

    /*methods for observing immutable live data*/
    fun cryptoViewState() = cryptoRatesLiveData

    init {
        loadUsers(isSwipeRefresh = false)
    }

    fun loadUsers(offset: Int = 0, isSwipeRefresh: Boolean) {
        launchUI {

            showLoading(isSwipeRefresh, isLoading = true)

            val cryptoCurrencies = withContext(contextProvider.getIO()) { repository.readCryptoRates(offset) }
            val totalCrypto = cryptoRatesLiveData.value?.items?.plus(cryptoCurrencies) ?: cryptoCurrencies

            cryptoRatesLiveData.value = CryptoViewState.toDataState(totalCrypto)

            showLoading(isSwipeRefresh, isLoading = false)

        }
    }

    fun getItems() = cryptoRatesLiveData.value?.items ?: emptyList()

    fun showLoading(isSwipeRefresh: Boolean, isLoading: Boolean) {

        if (isSwipeRefresh) cryptoRatesLiveData.value = CryptoViewState.swipeRefreshState(getItems(), isLoading)
        else                cryptoRatesLiveData.value = CryptoViewState.listLoadingState(getItems(), isLoading)

    }

    private fun launchUI(codeBlock: suspend () -> Unit) {
        launch(contextProvider.getUI()) {
            try {

                codeBlock()

            } catch (e: Exception) {
                e.printStackTrace()
                cryptoRatesLiveData.value = CryptoViewState.errorState(getItems(), ErrorInfo(R.string.error_msg, Runnable {
                    resetError()
                    launchUI(codeBlock)
                }
                ))
            }
        }
    }

    fun resetError() {
        cryptoRatesLiveData.value = CryptoViewState.errorState(getItems(), errorInfo = null)
    }

}
