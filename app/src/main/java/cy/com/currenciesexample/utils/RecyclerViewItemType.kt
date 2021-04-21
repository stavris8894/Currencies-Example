package cy.com.currenciesexample.utils

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cy.com.currenciesexample.databinding.CurrencyRecyclerViewBinding
import cy.com.currenciesexample.utils.viewholders.CurrencyViewHolder

enum class RecyclerViewItemType(var viewType: Int) {
    NONE(-1),
    CURRENCY(0);

    companion object {

        private val map = values().associateBy { it.viewType }

        private fun byViewType(viewType: Int) = map[viewType] ?: error("ViewHolder not found!")

        fun byViewType(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return when (byViewType(viewType)) {
                NONE -> TODO()
                CURRENCY -> {
                    CurrencyViewHolder(
                        BindingViewHolder.getBinding(
                            CurrencyRecyclerViewBinding::inflate,
                            parent
                        )
                    )
                }
            }
        }
    }
}