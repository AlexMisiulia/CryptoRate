@file:Suppress("EXPERIMENTAL_FEATURE_WARNING")

package best.lang.cryptorates.crypto

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import best.lang.cryptorates.entity.CryptoCurrency
import best.lang.cryptorates.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject


class CryptoVM @Inject constructor(private val repository: CryptoRepository,
                                   private val contextProvider: CoroutineContextProvider) : ViewModel() {
    val cryptoRatesLiveData = MutableLiveData<Collection<CryptoCurrency>>()

    init {
        loadUsers()
    }

    private fun loadUsers() {
        launch(contextProvider.getUI()) {
            try {
                cryptoRatesLiveData.value = async(contextProvider.getIO()) {
                    repository.readCryptoRates()
                }.await()
            } catch(e: Exception) {
                throw RuntimeException(e)
            }
        }
    }
}
