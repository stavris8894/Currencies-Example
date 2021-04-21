package cy.com.currenciesexample.currencies

import androidx.recyclerview.widget.RecyclerView
import cy.com.currenciesexample.ui_data.CurrencyUIViewData
import cy.com.currenciesexample.utils.adapters.GeneralRecyclerViewAdapter
import cy.com.currenciesexample.utils.interfaces.ViewHolderId
import cy.com.currenciesexample.utils.viewholders.CurrencyViewHolder

class CurrencyAdapter : GeneralRecyclerViewAdapter() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CurrencyViewHolder -> {
                holder.data = currentList[position] as CurrencyUIViewData<out ViewHolderId>
                holder.itemView.setOnClickListener {
                    listener?.invoke(currentList[position])
                }
            }
        }
    }
}