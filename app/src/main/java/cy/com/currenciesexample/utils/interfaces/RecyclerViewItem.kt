package cy.com.currenciesexample.utils.interfaces

import cy.com.currenciesexample.utils.RecyclerViewItemType

interface RecyclerViewItem {

    val itemType: RecyclerViewItemType

    val id: Any

    val data: Any
}