package cy.com.currenciesexample.utils.viewholders

import com.squareup.picasso.Picasso
import cy.com.currenciesexample.R
import cy.com.currenciesexample.databinding.CurrencyRecyclerViewBinding
import cy.com.currenciesexample.ui_data.CurrencyUIViewData
import cy.com.currenciesexample.utils.BindingViewHolder
import cy.com.currenciesexample.utils.interfaces.RecyclerViewItem
import cy.com.currenciesexample.utils.interfaces.ViewHolderId

class CurrencyViewHolder(binding: CurrencyRecyclerViewBinding) :
    BindingViewHolder<CurrencyRecyclerViewBinding>(binding) {

    var data: CurrencyUIViewData<out ViewHolderId>? = null
        set(value) {
            field = value
            field?.let {
                binding.titleTextView.text = it.data.currencyDescription
                Picasso.get()
                    .load(it.data.currencyCode.toFlagUrl())
                    .error(R.drawable.outline_info_black_36)
                    .placeholder(R.drawable.outline_info_black_36)
                    .into(binding.flagImageView)
            }
        }
}


fun String.toFlagUrl(width: Int = 48, height: Int = 36): String {
    return "https://flagcdn.com/${width}x${height}/${this.substring(0, 2).toLowerCase()}.png"
}
