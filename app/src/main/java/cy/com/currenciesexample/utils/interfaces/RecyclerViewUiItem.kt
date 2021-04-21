package cy.com.currenciesexample.utils.interfaces

abstract class RecyclerViewUiItem<T : ViewHolderId>(
    override val id: T
) : RecyclerViewItem
