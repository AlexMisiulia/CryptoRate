package best.lang.cryptorates.utils

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import best.lang.cryptorates.R

// to ensure that client code don't use view type with the same id
private const val ERROR_FOOTER_VIEW = R.layout.footer_error_item_view
private const val LOADING_VIEW = R.layout.footer_loading_item_view

abstract class EndlessAdapter<T: Any, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var isUpdating: Boolean = false
    private var isError: Boolean = false

    private val items = ArrayList<T?>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            LOADING_VIEW -> LoadingHolder(parent.inflate(R.layout.footer_loading_item_view))
            ERROR_FOOTER_VIEW ->  {

                ErrorHolder(parent.inflate(R.layout.footer_loading_item_view))
            }
            else -> onCreateItemViewHolder(parent, viewType)
        }
    }

    /**Guaranteed that holder has type VH**/
    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position) != LOADING_VIEW && getItemViewType(position) != ERROR_FOOTER_VIEW) {
            onBindItemViewHolder(holder as VH, position)
        } else {
            // now do nothing
        }
    }

    /**Make final to prevent client code from this fun usage**/
    final override fun getItemViewType(position: Int): Int {
        return if (isUpdating && isFooterPosition(position)) {
            LOADING_VIEW

        } else if (isError && isFooterPosition(position)) {
            ERROR_FOOTER_VIEW

        } else {
            getViewType(position)
        }
    }

    private fun isFooterPosition(position: Int) = position == itemCount - 1

    override fun getItemCount() = items.size


    fun showUpdating(isUpdating: Boolean) {

        if (isUpdating) addFooter()
        else            removeFooter()

        this.isUpdating = isUpdating
    }

    fun showError(isError: Boolean) {

        if (isError)    addFooter()
        else            removeFooter()

        this.isError = isError
    }



    private fun addFooter() {
        if (!items.contains(null)) items.add(null) //add footer
        notifyItemInserted(itemCount)
    }

    private fun removeFooter() {
        items.remove(null) //remove footer
        notifyItemRemoved(itemCount)
    }

    fun updateItems(newItems: Collection<T>) {
        if(items.isNotEmpty()) items.clear()
        items.addAll(newItems)
    }

    /*Footer is represented as null object*/
    fun getItemsNullable() = items

    /*When don't want to work with footer*/
    fun getItems() = items.filterNotNull()

    fun getItem(position: Int) = getItems()[position]

    /**Client code must override this methods for usage of this adapter* */
    abstract fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): VH
    abstract fun onBindItemViewHolder(holder: VH, position: Int)

    /**If client code want to use different view types it must use this method instead of  * */
    @Suppress("MemberVisibilityCanBePrivate")
    protected open fun getViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class LoadingHolder(v: View) : RecyclerView.ViewHolder(v) {

    }

    class ErrorHolder(v: View) : RecyclerView.ViewHolder(v) {

    }

}