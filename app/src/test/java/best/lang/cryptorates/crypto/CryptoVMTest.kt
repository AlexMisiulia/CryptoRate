package best.lang.cryptorates.crypto

import best.lang.cryptorates.BaseUnitTest
import best.lang.cryptorates.entity.CryptoCurrency
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


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
        val expectedResult = listOf(CryptoCurrency("bitcoin", "13000"))

        `when`(cryptoRepo.readCryptoRates()).thenReturn(expectedResult)
        cryptoViewModel = CryptoVM(cryptoRepo, testContext)

        // act
        val result = cryptoViewModel.cryptoRatesLiveData.observeValue()

        Assert.assertEquals(expectedResult, result)
    }

}