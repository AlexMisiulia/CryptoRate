package best.lang.cryptorates.crypto

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import best.lang.cryptorates.CryptoApp
import best.lang.cryptorates.OnCryptoClick
import best.lang.cryptorates.R
import best.lang.cryptorates.utils.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.activity_crypto.*
import javax.inject.Inject


class CryptoFragment : Fragment() {

    private lateinit var viewModel : CryptoVM

    private lateinit var cryptoAdapter: CryptoAdapter

    @Inject lateinit var viewModelFactory: CryptoVmFactory

    private lateinit var onCryptoClick: OnCryptoClick

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            onCryptoClick = (context as OnCryptoClick)
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement OnCryptoClick")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_crypto, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CryptoApp.graph.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CryptoVM::class.java)

        initView()

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.cryptoViewState().safeObserve {

            if(cryptoAdapter.getItems().isEmpty() || cryptoAdapter.getItems() != it.items) {
                cryptoAdapter.setItems(it.items)
            }

            cryptoAdapter.isUpdating = it.isRecyclerLoading
            cryptoAdapter.showError(it.errorInfo != null, it.errorInfo?.retryRunnable)


            swipeRefreshLayout.isRefreshing = it.isSwipeRefresh

        }
    }


    private fun <T> LiveData<T>.safeObserve(observeBlock: (T) -> Unit) {
        observe(this@CryptoFragment, Observer {
            it?.let {
                observeBlock(it)
            }
        })
    }

    private fun initView() {
        cryptoAdapter = CryptoAdapter(onCryptoClick)
        val layoutManager = LinearLayoutManager(activity)
        currencyRecyclerView.layoutManager = layoutManager
        currencyRecyclerView.addItemDecoration(
                DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        )
        currencyRecyclerView.adapter = cryptoAdapter

        currencyRecyclerView.addOnScrollListener(object: EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                if(!cryptoAdapter.isError) {
                    viewModel.loadUsers(totalItemsCount, isSwipeRefresh = false)
                }
            }
        })

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadUsers(isSwipeRefresh = true)
        }
    }
}
