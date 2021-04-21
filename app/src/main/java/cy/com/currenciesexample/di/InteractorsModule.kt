package cy.com.currenciesexample.di

import cy.com.currenciesexample.interactors.CurrencyInteractor
import org.koin.dsl.module

val interactorsModule = module {
    factory { CurrencyInteractor(get(), get()) }
}