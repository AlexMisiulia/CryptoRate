package best.lang.cryptorates.utils

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.experimental.CoroutineContext

@Singleton
open class CoroutineContextProvider @Inject constructor() {
    open fun getUI() : CoroutineContext = UI

    open fun getIO() : CoroutineContext = CommonPool

}