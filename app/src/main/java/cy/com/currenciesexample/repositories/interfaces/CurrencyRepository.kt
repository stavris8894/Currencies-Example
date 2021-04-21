package cy.com.currenciesexample.repositories.interfaces

import cy.com.currenciesexample.models.Rates
import cy.com.currenciesexample.models.ResultWrapper
import cy.com.currenciesexample.models.Symbols

interface CurrencyRepository {
    suspend fun getSymbols(): Symbols
    suspend fun getLatestRates(): Rates
}