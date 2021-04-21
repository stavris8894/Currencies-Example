package cy.com.currenciesexample.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrencyEntity(
    @PrimaryKey
    val currencyCode: String,
    val currencyDescription: String,
    val currencyRate: Double
)