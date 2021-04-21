package cy.com.currenciesexample.interactors

import androidx.lifecycle.asLiveData
import cy.com.currenciesexample.models.Rates
import cy.com.currenciesexample.models.ResultWrapper
import cy.com.currenciesexample.models.Symbols
import cy.com.currenciesexample.models.safeApiCall
import cy.com.currenciesexample.repositories.CurrencyDatabaseRepository
import cy.com.currenciesexample.repositories.interfaces.CurrencyRepository
import cy.com.currenciesexample.room.CurrencyEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow

class CurrencyInteractor(
    private val currencyRepository: CurrencyRepository,
    private val currencyDatabaseRepository: CurrencyDatabaseRepository
) {

    fun fetchDataFromDB(): Flow<List<CurrencyEntity>> {
        return currencyDatabaseRepository.getCurrenciesFromDB()
    }

    fun fetchCurrencyDetails(code: String): Flow<CurrencyEntity> {
        return currencyDatabaseRepository.getCurrencyDetails(code)
    }

    suspend fun fetchDataFromAPI() {
        coroutineScope {
            val getSymbols = async {
                safeApiCall(Dispatchers.IO) {
                    currencyRepository.getSymbols()
                }
            }
            val getLatestRates = async {
                safeApiCall(Dispatchers.IO) {
                    currencyRepository.getLatestRates()
                }
            }
            addRatesIntoDb(getSymbols.await(), getLatestRates.await())
        }
    }

    private suspend fun addRatesIntoDb(
        symbols: ResultWrapper<Symbols>,
        rates: ResultWrapper<Rates>
    ) {
        if ((symbols is ResultWrapper.Success) && (rates is ResultWrapper.Success)) {
            val list: ArrayList<CurrencyEntity> = arrayListOf()
            symbols.value.symbols.forEach { (t, u) ->
                rates.value.rates[t]?.let {
                    list.add(CurrencyEntity(t, u, it))
                }
            }
            currencyDatabaseRepository.insertIntoDB(list)
        }
    }
}