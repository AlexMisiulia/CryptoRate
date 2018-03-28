package best.lang.cryptorates.crypto

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import best.lang.cryptorates.R
import best.lang.cryptorates.entity.CryptoCurrency
import best.lang.cryptorates.utils.CoroutineContextProvider
import best.lang.cryptorates.utils.CoroutinesActions.Companion.withContext
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

open class CryptoVM @Inject constructor(private val repository: CryptoRepository,
                                        private val contextProvider: CoroutineContextProvider) : ViewModel() {
    private val cryptoRatesLiveData = MutableLiveData<Collection<CryptoCurrency>>()
    private val loadingLiveData = MutableLiveData<Boolean>()
    private val swipeRefreshLiveData = MutableLiveData<Boolean>()
    private val errorLiveData = MutableLiveData<Int>()

    /*methods for observing immutable live data*/
    fun cryptoRatesData() : LiveData<Collection<CryptoCurrency>> = cryptoRatesLiveData
    fun loadingData() : LiveData<Boolean> = loadingLiveData
    fun swipeRefreshData() : LiveData<Boolean> = swipeRefreshLiveData
    fun errorData() : LiveData<Int> = errorLiveData

    init {
        loadUsers(isSwipeRefresh = false)
    }

    fun loadUsers(offset: Int = 0, isSwipeRefresh: Boolean) {
        launchUI {

            showLoading(isSwipeRefresh, isLoading = true)

            val cryptoCurrencies = withContext(contextProvider.getIO()) { repository.readCryptoRates(offset) }
            val totalCrypto = cryptoRatesLiveData.value?.plus(cryptoCurrencies) ?: cryptoCurrencies
            cryptoRatesLiveData.value = totalCrypto

            showLoading(isSwipeRefresh, isLoading = false)

        }
    }

    fun showLoading(isSwipeRefresh: Boolean, isLoading: Boolean) {
        if(isSwipeRefresh) {
            swipeRefreshLiveData.value = isLoading
        } else {
            loadingLiveData.value = isLoading
        }
    }

    private fun launchUI(codeBlock: suspend () -> Unit) {
        launch(contextProvider.getUI()) {
            try {

                codeBlock()

            } catch (e: Exception) {
                e.printStackTrace()
                errorLiveData.value = R.string.error_msg
                loadingLiveData.value = false
            }
        }
    }

    fun onErrorShown() {
        errorLiveData.value = null
    }

}
