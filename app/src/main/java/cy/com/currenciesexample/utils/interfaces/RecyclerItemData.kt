package cy.com.currenciesexample.utils.interfaces

interface RecyclerItemData {

    fun isSameData(data: RecyclerItemData): Boolean {
        return this == data
    }
}