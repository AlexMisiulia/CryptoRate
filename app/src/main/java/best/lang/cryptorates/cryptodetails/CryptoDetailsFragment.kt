package best.lang.cryptorates.cryptodetails

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import best.lang.cryptorates.R
import best.lang.cryptorates.entity.CryptoCurrency
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.crypto_details_fragment.*

fun createCryptoDetailsFragment(cryptoCurrency: CryptoCurrency) : CryptoDetailsFragment{
    val fragment = CryptoDetailsFragment()
    val bundle = Bundle()
    bundle.putSerializable(CRYPTO, cryptoCurrency)
    fragment.arguments = bundle
    return fragment
}


const val CRYPTO = "crypto"
class CryptoDetailsFragment : Fragment() {
    lateinit var cryptoCurrency: CryptoCurrency

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.crypto_details_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cryptoCurrency = arguments.getSerializable(CRYPTO) as CryptoCurrency
        render(cryptoCurrency)
    }

    private fun render(currency: CryptoCurrency) {
        Picasso.get().load(currency.getImageUrl()).into(currencyImageView)
        nameTextView.text = currency.name
        valueTextView.text = currency.priceUsd
    }

    fun updateCurrency(currency: CryptoCurrency) {
        cryptoCurrency = currency
        render(cryptoCurrency)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(CRYPTO, cryptoCurrency)
    }
}