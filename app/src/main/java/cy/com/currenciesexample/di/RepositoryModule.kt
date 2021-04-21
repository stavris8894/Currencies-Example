package cy.com.currenciesexample.di

import cy.com.currenciesexample.repositories.CurrencyDatabaseRepository
import cy.com.currenciesexample.repositories.CurrencyRemoteRepository
import cy.com.currenciesexample.repositories.interfaces.CurrencyRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { CurrencyRemoteRepository(get()) as CurrencyRepository }
    factory { CurrencyDatabaseRepository(get()) }
}