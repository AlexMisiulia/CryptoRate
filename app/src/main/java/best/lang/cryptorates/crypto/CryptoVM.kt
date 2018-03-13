
package best.lang.cryptorates.crypto

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import best.lang.cryptorates.entity.CryptoCurrency
import best.lang.cryptorates.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import javax.inject.Inject


open class CryptoVM @Inject constructor(private val repository: CryptoRepository,
                                   private val contextProvider: CoroutineContextProvider) : ViewModel() {
    val cryptoRatesLiveData = MutableLiveData<Collection<CryptoCurrency>>()

    init {
        loadUsers()
    }

    private fun loadUsers() {
        launch(contextProvider.getUI()) {
            try {
                cryptoRatesLiveData.value = withContext(contextProvider.getIO()) {
                    repository.readCryptoRates()
                }
            } catch(e: Exception) {
                throw RuntimeException(e)
            }
        }
    }
}
