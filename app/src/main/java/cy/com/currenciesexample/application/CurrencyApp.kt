package cy.com.currenciesexample.application

import android.app.Application
import cy.com.currenciesexample.di.interactorsModule
import cy.com.currenciesexample.di.networkModule
import cy.com.currenciesexample.di.repositoryModule
import cy.com.currenciesexample.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CurrencyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(this@CurrencyApp)
            modules(listOf(repositoryModule, viewModelModule, networkModule, interactorsModule))
        }
    }

    companion object {
        private lateinit var instance: CurrencyApp
    }
}