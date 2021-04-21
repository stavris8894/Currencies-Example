package cy.com.currenciesexample.di

import cy.com.currenciesexample.network.RestClient
import org.koin.dsl.module

val networkModule = module {
    single { RestClient.instance }
}