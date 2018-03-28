package best.lang.cryptorates.crypto

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import best.lang.cryptorates.CryptoApp
import best.lang.cryptorates.R
import best.lang.cryptorates.utils.bind
import javax.inject.Inject


class CryptoActivity : AppCompatActivity() {
    private val currencyRecyclerView: RecyclerView by bind(R.id.currency_recycler_view)
    private val swipeRefreshLayout: SwipeRefreshLayout by bind(R.id.swipe_refresh_layout)

    private lateinit var viewModel : CryptoVM

    private lateinit var cryptoAdapter: CryptoAdapter

    @Inject lateinit var viewModelFactory: CryptoVmFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CryptoApp.graph.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CryptoVM::class.java)

        initView()

        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.cryptoRatesData().safeObserve {

            cryptoAdapter.setItems(it)

        }

        viewModel.loadingData().safeObserve {

            if(swipeRefreshLayout.isRefreshing != it) swipeRefreshLayout.isRefreshing = it

        }

        viewModel.errorData().safeObserve {

            Toast.makeText(this@CryptoActivity, it, Toast.LENGTH_SHORT).show()
            viewModel.onErrorShown()
        }
    }


    private fun <T> LiveData<T>.safeObserve(observeBlock: (T) -> Unit) {
        observe(this@CryptoActivity, Observer {
            it?.let {
                observeBlock(it)
            }
        })
    }

    private fun initView() {
        cryptoAdapter = CryptoAdapter()
        currencyRecyclerView.layoutManager = LinearLayoutManager(this)
        currencyRecyclerView.addItemDecoration(
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        currencyRecyclerView.adapter = cryptoAdapter

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadUsers()
        }
    }
}
