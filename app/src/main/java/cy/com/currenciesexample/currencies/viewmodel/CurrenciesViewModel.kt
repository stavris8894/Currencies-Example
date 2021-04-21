package cy.com.currenciesexample.currencies.viewmodel

import android.util.Log
import androidx.lifecycle.*
import cy.com.currenciesexample.currencies.uidatasource.CurrencyDatasource
import cy.com.currenciesexample.interactors.CurrencyInteractor
import cy.com.currenciesexample.models.ErrorResponse
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

    private val _errorResponseLiveData: MutableLiveData<ErrorResponse> = MutableLiveData()
    val errorResponseLiveData: LiveData<ErrorResponse> = _errorResponseLiveData

    fun fetchData() {
        viewModelScope.launch {
            mCurrencyInteractor.fetchDataFromAPI {
                _errorResponseLiveData.value = it
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