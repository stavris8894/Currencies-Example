package cy.com.currenciesexample.repositories

import cy.com.currenciesexample.models.Rates
import cy.com.currenciesexample.models.Symbols
import cy.com.currenciesexample.network.ApiWebServices
import cy.com.currenciesexample.repositories.interfaces.CurrencyRepository

class CurrencyRemoteRepository(
    private val apiWebServices: ApiWebServices
) : CurrencyRepository {

    override suspend fun getSymbols(): Symbols {
        return apiWebServices.getSymbols()
    }

    override suspend fun getLatestRates(): Rates {
        return apiWebServices.getLatest()
    }
}