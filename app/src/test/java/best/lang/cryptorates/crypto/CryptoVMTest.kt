package best.lang.cryptorates.crypto

import best.lang.cryptorates.BaseUnitTest
import best.lang.cryptorates.entity.CryptoCurrency
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as whenever


class CryptoVMTest : BaseUnitTest() {

    lateinit var cryptoViewModel : CryptoVM

    @Mock
    lateinit var cryptoRepo: CryptoRepository

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getCryptoRatesLiveData() = runBlocking {
        val expectedResult = listOf(CryptoCurrency("bitcoin", "Bitcoin", "BTC", "1200"))

        whenever(cryptoRepo.readCryptoRates()).thenReturn(expectedResult)
        cryptoViewModel = CryptoVM(cryptoRepo, testContext)

        // act
        val result = cryptoViewModel.cryptoRatesData().observeValue()

        Assert.assertEquals(expectedResult, result)
    }

}