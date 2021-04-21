package cy.com.currenciesexample.ui_data

import cy.com.currenciesexample.room.CurrencyEntity
import cy.com.currenciesexample.utils.RecyclerViewItemType
import cy.com.currenciesexample.utils.interfaces.RecyclerViewUiItem
import cy.com.currenciesexample.utils.interfaces.ViewHolderId

class CurrencyUIViewData<T : ViewHolderId>(
    id: T,
    override val data: CurrencyEntity
) : RecyclerViewUiItem<T>(id) {
    override val itemType: RecyclerViewItemType
        get() = RecyclerViewItemType.CURRENCY
}