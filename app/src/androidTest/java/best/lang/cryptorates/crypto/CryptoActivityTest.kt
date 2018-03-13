package best.lang.cryptorates.crypto

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import best.lang.cryptorates.TestApp
import junit.framework.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class CryptoActivityTest {
    @Rule
    @JvmField
    val activityRule = ActivityTestRule<CryptoActivity>(CryptoActivity::class.java)

    @Inject lateinit var cryptoRepository: CryptoRepository

    @Before
    fun before() {
        cryptoRepository = TestApp.graph().cryptoRepository()
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testAdd() {
//        `when`(cryptoRepository.readCryptoRatesSync()).thenReturn(emptyList())
        Assert.assertTrue(true)
        Log.d("FuncTest","testAdd")
    }

}