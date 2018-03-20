package best.lang.cryptorates.utils

import android.support.annotation.VisibleForTesting
import kotlin.coroutines.experimental.CoroutineContext

class CoroutinesActions {

    companion object {

        var executionObserver : ExecutionObserver? = null
            private set

        @VisibleForTesting
        fun setObserver(onStart: () -> Unit, onFinish: () -> Unit) {
            executionObserver = object: ExecutionObserver {
                override fun onStart() {
                    onStart()
                }

                override fun onFinish() {
                    onFinish()
                }
            }
        }

        public suspend fun <T> withContext(
                context: CoroutineContext,
                block: suspend () -> T
        ): T {

            executionObserver?.onStart()

            val result = kotlinx.coroutines.experimental.withContext(context) {
                block()
            }

            executionObserver?.onFinish()
            return result
        }
    }

    interface ExecutionObserver {
        fun onStart()
        fun onFinish()
    }
}