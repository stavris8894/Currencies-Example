package cy.com.currenciesexample.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [CurrencyEntity::class], version = 1, exportSchema = false)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao

    companion object {
        private var instance: CurrencyDatabase? = null

        private const val DATABASE_NAME = "currency_db"

        fun getInstance(context: Context): CurrencyDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private val roomCallBack = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                CoroutineScope(Dispatchers.IO).launch {
                    instance?.apply {
                        //TODO: clear db if needed
                    }
                }
            }
        }

        private fun buildDatabase(context: Context): CurrencyDatabase {
            return Room.databaseBuilder(
                context, CurrencyDatabase::class.java,
                DATABASE_NAME
            ).addCallback(roomCallBack).build()
        }
    }
}