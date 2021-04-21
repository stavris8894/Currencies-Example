package cy.com.currenciesexample.repositories

import android.app.Application
import cy.com.currenciesexample.room.CurrencyEntity
import cy.com.currenciesexample.room.CurrencyDatabase
import kotlinx.coroutines.flow.Flow

class CurrencyDatabaseRepository(application: Application) {

    private var currencyDao = CurrencyDatabase.getInstance(application).currencyDao()

    suspend fun insertIntoDB(list: List<CurrencyEntity>) {
        currencyDao.insertAll(list)
    }

    fun getCurrenciesFromDB(): Flow<List<CurrencyEntity>> {
        return currencyDao.getCurrenciesFlow()
    }

    fun getCurrencyDetails(code: String): Flow<CurrencyEntity> {
        return currencyDao.getCurrencyDetailsByCode(code)
    }
}