package best.lang.cryptorates.crypto

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoVmFactory
@Inject constructor(private val cryptoVM: CryptoVM) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CryptoVM::class.java)) {
            return cryptoVM as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}
