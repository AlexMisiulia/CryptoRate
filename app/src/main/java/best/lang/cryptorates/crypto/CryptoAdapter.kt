package best.lang.cryptorates.crypto

import android.support.annotation.LayoutRes
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import best.lang.cryptorates.R
import best.lang.cryptorates.entity.CryptoCurrency
import best.lang.cryptorates.utils.bind
import com.squareup.picasso.Picasso



class CryptoAdapter : RecyclerView.Adapter<CryptoAdapter.CurrencyViewHolder>() {
    private val items = ArrayList<CryptoCurrency>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = inflate(parent, R.layout.currency_item_view)
        return CurrencyViewHolder(view)
    }

    private fun inflate(parent: ViewGroup, @LayoutRes res: Int): View {
        return LayoutInflater.from(parent.context).inflate(res, parent, false)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.setContent(items[position])
    }

    override fun getItemCount() = items.size


    class CurrencyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val nameTextView: TextView      by bind(R.id.name_text_view)
        private val valueTextView: TextView     by bind(R.id.value_text_view)
        private val imageView: ImageView        by bind(R.id.currency_image_view)

        fun setContent(item: CryptoCurrency) {
            nameTextView.text = item.name
            valueTextView.text = item.priceUsd
            Picasso.get().load(item.getImageUrl()).into(imageView)
        }

    }

    fun setItems(newItems: Collection<CryptoCurrency>) {

        val diffResult = DiffUtil.calculateDiff(CryptoDiffUtilsCallback(newItems.toList(), items))
        diffResult.dispatchUpdatesTo(this)

        if(items.isNotEmpty()) items.clear()
        items.addAll(newItems)
    }

    class CryptoDiffUtilsCallback(private val newCurrencies: List<CryptoCurrency>,
                                  private val oldCurrencies: List<CryptoCurrency>)
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