package best.lang.cryptorates.crypto

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import best.lang.cryptorates.entity.CryptoCurrency
import javax.inject.Inject


class CryptoVM @Inject constructor(private val repository: CryptoRepository) : ViewModel() {
    val cryptoRatesLiveData = MutableLiveData<Collection<CryptoCurrency>>()

    init {
        loadUsers()
    }

    private fun loadUsers() {
        cryptoRatesLiveData.value = repository.readCryptoRates()
    }
}
