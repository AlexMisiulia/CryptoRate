package best.lang.cryptorates

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import best.lang.cryptorates.entity.CryptoCurrency
import best.lang.utils.bind

class CurrencyAdapter : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {
    private val items = ArrayList<CryptoCurrency>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = inflate(parent, R.layout.currency_item_view)
        return CurrencyViewHolder(view)
    }

    private fun inflate(parent: ViewGroup, @LayoutRes res: Int) : View {
        return LayoutInflater.from(parent.context).inflate(res, parent, false)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.setContent(items[position])
    }

    override fun getItemCount() = items.size


    class CurrencyViewHolder(val v: View) : RecyclerView.ViewHolder(v) {
        private val nameTextView: TextView      by bind(R.id.name_text_view)
        private val valueTextView: TextView     by bind(R.id.value_text_view)

        fun setContent(item: CryptoCurrency) {
            nameTextView.text = item.name
            valueTextView.text = item.value
        }

    }

    fun setItems(newItems: Collection<CryptoCurrency>) {
        if(!items.isEmpty()) items.clear()

        items.addAll(newItems)
    }

}