package cy.com.currenciesexample.di

import cy.com.currenciesexample.currencies.viewmodel.CurrenciesViewModel
import cy.com.currenciesexample.currencies.viewmodel.CurrencyDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CurrenciesViewModel(get()) }
    viewModel { CurrencyDetailsViewModel(get()) }
}