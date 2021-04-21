package cy.com.currenciesexample.currencies.uidatasource

import cy.com.currenciesexample.room.CurrencyEntity
import cy.com.currenciesexample.ui_data.CurrencyUIViewData
import cy.com.currenciesexample.utils.interfaces.RecyclerViewItem
import cy.com.currenciesexample.utils.interfaces.ViewHolderId

enum class CurrencyOptions : ViewHolderId {
    ROW
}

class CurrencyDatasource {

    fun convertCurrencyDataToUi(list: List<CurrencyEntity>): List<RecyclerViewItem> {
        val arrayList: ArrayList<RecyclerViewItem> = arrayListOf()
        list.forEach {
            arrayList.add(
                CurrencyUIViewData(
                    CurrencyOptions.ROW,
                    it
                )
            )
        }
        return arrayList.toList()
    }
}