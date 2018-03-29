package best.lang.cryptorates.crypto

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import best.lang.cryptorates.OnCryptoClick
import best.lang.cryptorates.R
import best.lang.cryptorates.entity.CryptoCurrency
import best.lang.cryptorates.utils.EndlessAdapter
import best.lang.cryptorates.utils.inflate
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.currency_item_view.*



class CryptoAdapter(private val onCryptoClick: OnCryptoClick) : EndlessAdapter<CryptoCurrency, CryptoAdapter.CurrencyViewHolder>() {


    override fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = parent.inflate(R.layout.currency_item_view)
        return CurrencyViewHolder(view)
    }

    override fun onBindItemViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.setContent(getItems()[position])
    }

    inner class CurrencyViewHolder(override val containerView: View) :
            RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            containerView.setOnClickListener {
                onCryptoClick.onClick(getItem(layoutPosition))
            }
        }

        fun setContent(item: CryptoCurrency) {
            nameTextView.text = item.name
            valueTextView.text = item.priceUsd
            Picasso.get().load(item.getImageUrl()).into(currencyImageView)
        }

    }

    fun setItems(newItems: Collection<CryptoCurrency>) {

        val diffResult = DiffUtil.calculateDiff(
                CryptoDiffUtilsCallback(newItems.toList(), getItemsNullable())
        )
        diffResult.dispatchUpdatesTo(this)

        updateItems(newItems)
    }

    class CryptoDiffUtilsCallback(private val newCurrencies: List<CryptoCurrency>,
                                  private val oldCurrencies: List<CryptoCurrency?>)
        : DiffUtil.Callback() {

        override fun getOldListSize() = oldCurrencies.size


        override fun getNewListSize() = newCurrencies.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return newCurrencies[newItemPosition] == oldCurrencies[oldItemPosition]
        }


        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return newCurrencies[newItemPosition] === oldCurrencies[oldItemPosition]
        }

    }

}