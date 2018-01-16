package best.lang.cryptorates

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.LiveData
import best.lang.TestCoroutineContextProvider
import org.junit.Rule
import org.junit.rules.TestRule

open class BaseUnitTest() {
    protected val testContext: TestCoroutineContextProvider = TestCoroutineContextProvider()

    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()


    fun <T> LiveData<T>. observeValue() : T? {
        observeForever({ })
        return value
    }
}