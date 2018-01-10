package best.lang.cryptorates

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import best.lang.cryptorates.entity.CryptoCurrency
import best.lang.utils.bind

class MainActivity : AppCompatActivity() {
    private val currencyRecyclerView: RecyclerView by bind(R.id.currency_recycler_view)

    private lateinit var currencyAdapter : CurrencyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        currencyAdapter = CurrencyAdapter()

        currencyRecyclerView.layoutManager = LinearLayoutManager(this)

        currencyRecyclerView.addItemDecoration(
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        currencyRecyclerView.adapter = currencyAdapter

        currencyAdapter.setItems(arrayListOf(
                CryptoCurrency("bitcoin", "13000"),
                CryptoCurrency("ephireum", "800")
        ))
    }
}
