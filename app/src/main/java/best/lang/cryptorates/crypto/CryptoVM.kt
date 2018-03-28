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
import kotlin.coroutines.experimental.CoroutineContext

open class CryptoVM @Inject constructor(private val repository: CryptoRepository,
                                        private val contextProvider: CoroutineContextProvider) : ViewModel() {
    private val cryptoRatesLiveData = MutableLiveData<Collection<CryptoCurrency>>()
    private val loadingLiveData = MutableLiveData<Boolean>()
    private val errorLiveData = MutableLiveData<Int>()

    /*methods for observing immutable live data*/
    fun cryptoRatesData() : LiveData<Collection<CryptoCurrency>> = cryptoRatesLiveData
    fun loadingData() : LiveData<Boolean> = loadingLiveData
    fun errorData() : LiveData<Int> = errorLiveData

    init {
        loadUsers()
    }

    fun loadUsers(offset: Int = 0) {
        launchUI {

            val cryptoCurrencies = doAsync { repository.readCryptoRates(offset) }
            val totalCrypto = cryptoRatesLiveData.value?.plus(cryptoCurrencies) ?: cryptoCurrencies
            cryptoRatesLiveData.value = totalCrypto
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

    private suspend fun <T> doAsync(context: CoroutineContext = contextProvider.getIO(), task: suspend () -> T) : T {
        loadingLiveData.value = true

        val result = withContext(context) {     task()  }

        loadingLiveData.value = false

        return result
    }

    fun onErrorShown() {
        errorLiveData.value = null
    }
}
