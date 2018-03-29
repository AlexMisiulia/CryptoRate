package best.lang.cryptorates

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import best.lang.cryptorates.crypto.CryptoFragment
import best.lang.cryptorates.cryptodetails.CRYPTO
import best.lang.cryptorates.cryptodetails.CryptoDetailsActivity
import best.lang.cryptorates.cryptodetails.CryptoDetailsFragment
import best.lang.cryptorates.cryptodetails.createCryptoDetailsFragment
import best.lang.cryptorates.entity.CryptoCurrency
import best.lang.cryptorates.utils.fragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*


interface OnCryptoClick {
    fun onClick(cryptoCurrency: CryptoCurrency)
}

class MainActivity : AppCompatActivity(), OnCryptoClick {

    override fun onClick(cryptoCurrency: CryptoCurrency) {
        if (detailsFrameLayout != null) {

            renderDetailsFragment(cryptoCurrency)
        } else {
            val intent = Intent(this@MainActivity, CryptoDetailsActivity::class.java)
            intent.putExtra(CRYPTO, cryptoCurrency)
            startActivity(intent)
        }
    }

    @Suppress("PLUGIN_WARNING")
    private fun renderDetailsFragment(cryptoCurrency: CryptoCurrency) {
        val cryptoDetailsFragment = if (supportFragmentManager.findFragmentById(detailsFrameLayout.id) != null) {
            supportFragmentManager.findFragmentById(detailsFrameLayout.id) as CryptoDetailsFragment
        } else null

        if (cryptoDetailsFragment != null) {
            cryptoDetailsFragment.updateCurrency(cryptoCurrency)
        } else {

            fragmentTransaction {
                replace(detailsFrameLayout.id, createCryptoDetailsFragment(cryptoCurrency))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            fragmentTransaction {
                add(masterFrameLayout.id, CryptoFragment())
            }

        }
    }


}