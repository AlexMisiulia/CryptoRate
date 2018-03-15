package best.lang.cryptorates.crypto

import android.content.Intent
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.idling.CountingIdlingResource
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import best.lang.cryptorates.TestApp
import best.lang.cryptorates.entity.CryptoCurrency
import best.lang.cryptorates.network.WebService
import best.lang.cryptorates.utils.CoroutinesActions
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import javax.inject.Inject
import org.mockito.Mockito.`when` as whenever

@RunWith(AndroidJUnit4::class)
class CryptoActivityTest {
    @Rule
    @JvmField
    val activityRule = ActivityTestRule<CryptoActivity>(CryptoActivity::class.java, true, false)

    val idleRes = CountingIdlingResource("coroutines idle res")

    @Inject lateinit var mockWebService: WebService

    @Before
    fun before() {
        mockWebService = TestApp.testGraph().mockWebService()
        CoroutinesActions.setObserver(onStart = {idleRes.increment()}, onFinish = {idleRes.decrement()})
        IdlingRegistry.getInstance().register(idleRes)
    }

    @After
    fun after() {
        IdlingRegistry.getInstance().unregister(idleRes)
    }

    @Test
    fun currenciesAreDisplayedInRecyclerView() {

        // arrange
        val testData = listOf(
                CryptoCurrency("Bitcoin", "10000"),
                CryptoCurrency("Ephirium", "800")
        )

        whenever(mockWebService.readCurrencies(anyInt())).thenReturn(testData)

        // act
        activityRule.launchActivity(Intent())

        // assert
        checkCurrenciesDisplayed(testData)
    }

}