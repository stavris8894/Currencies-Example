package cy.com.currenciesexample.network

import cy.com.currenciesexample.models.Rates
import cy.com.currenciesexample.models.Symbols
import retrofit2.http.GET

interface ApiWebServices {

    @GET("latest")
    suspend fun getLatest(): Rates

    @GET("symbols")
    suspend fun getSymbols(): Symbols
}