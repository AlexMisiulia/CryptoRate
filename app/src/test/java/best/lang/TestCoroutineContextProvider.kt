package best.lang

import best.lang.cryptorates.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

class TestCoroutineContextProvider : CoroutineContextProvider() {

    override fun getUI(): CoroutineContext  = Unconfined

    override fun getIO(): CoroutineContext = Unconfined
}