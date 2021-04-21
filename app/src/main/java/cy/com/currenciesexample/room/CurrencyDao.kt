package cy.com.currenciesexample.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<CurrencyEntity>)

    @Query("SELECT * FROM CurrencyEntity")
    fun getCurrenciesFlow(): Flow<List<CurrencyEntity>>

    @Query("SELECT * FROM CurrencyEntity WHERE currencyCode=:code")
    fun getCurrencyDetailsByCode(code: String): Flow<CurrencyEntity>

}