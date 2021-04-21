package cy.com.currenciesexample.currencies.viewmodel

import android.util.Log
import androidx.lifecycle.*
import cy.com.currenciesexample.currencies.uidatasource.CurrencyDatasource
import cy.com.currenciesexample.interactors.CurrencyInteractor
import cy.com.currenciesexample.models.ResultWrapper
import cy.com.currenciesexample.utils.interfaces.RecyclerViewItem
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CurrenciesViewModel(
    private val mCurrencyInteractor: CurrencyInteractor
) : ViewModel() {

    private val mCurrencyDatasource = CurrencyDatasource()

    val data: LiveData<List<RecyclerViewItem>> = liveData {
        mCurrencyInteractor.fetchDataFromDB().collectLatest {
            emit(mCurrencyDatasource.convertCurrencyDataToUi(it))
        }
    }

    fun fetchData() {
        viewModelScope.launch {
            val result = try {
                mCurrencyInteractor.fetchDataFromAPI()
            } catch (e: Exception) {
                Log.e(TAG, "exception: $e")
            }
            when (result) {
                is ResultWrapper.Error -> {
                    Log.e(TAG, "exception: ResultWrapper.Error")

                }
            }
        }
    }


    init {
        fetchData()
    }

    companion object {
        private val TAG = CurrenciesViewModel::class.java.simpleName
    }
}