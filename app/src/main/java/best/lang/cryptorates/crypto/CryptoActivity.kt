package best.lang.cryptorates.crypto

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import best.lang.cryptorates.CryptoApp
import best.lang.cryptorates.R
import best.lang.cryptorates.utils.bind
import javax.inject.Inject


class CryptoActivity : AppCompatActivity() {
    private val currencyRecyclerView: RecyclerView by bind(R.id.currency_recycler_view)

    private lateinit var cryptoAdapter: CryptoAdapter

    @Inject lateinit var viewModelFactory: CryptoVmFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CryptoApp.graph.inject(this)

        cryptoAdapter = CryptoAdapter()

        currencyRecyclerView.layoutManager = LinearLayoutManager(this)

        currencyRecyclerView.addItemDecoration(
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        currencyRecyclerView.adapter = cryptoAdapter


        val model = ViewModelProviders.of(this, viewModelFactory).get(CryptoVM::class.java)

        model.cryptoRatesLiveData.observe(this, Observer { cryptoRates ->

            cryptoRates?.let {
                cryptoAdapter.setItems(cryptoRates)
            }

        })
    }
}
