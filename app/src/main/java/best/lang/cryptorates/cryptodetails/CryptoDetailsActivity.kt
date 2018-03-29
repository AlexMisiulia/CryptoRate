package best.lang.cryptorates.cryptodetails

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import best.lang.cryptorates.R
import best.lang.cryptorates.entity.CryptoCurrency
import best.lang.cryptorates.utils.fragmentTransaction
import kotlinx.android.synthetic.main.crypto_details_activity.*

class CryptoDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crypto_details_activity)

        val cryptoCurrency = intent.getSerializableExtra(CRYPTO) as CryptoCurrency
        fragmentTransaction {
            add(detailsFragmentContainer.id, createCryptoDetailsFragment(cryptoCurrency))
        }
    }
}