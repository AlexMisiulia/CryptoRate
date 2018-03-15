package best.lang.cryptorates.crypto

import android.support.test.espresso.matcher.ViewMatchers.hasDescendant
import android.support.test.espresso.matcher.ViewMatchers.withText
import best.lang.cryptorates.R
import best.lang.cryptorates.entity.CryptoCurrency
import best.lang.cryptorates.helper.forEachItem


fun checkCurrenciesDisplayed(currencies: Collection<CryptoCurrency>) {
    forEachItem(R.id.currency_recycler_view, currencies) {
        hasDescendant(withText(it.name))
    }
}