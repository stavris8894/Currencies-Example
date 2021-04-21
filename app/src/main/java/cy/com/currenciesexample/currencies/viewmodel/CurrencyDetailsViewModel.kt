package cy.com.currenciesexample.currencies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cy.com.currenciesexample.interactors.CurrencyInteractor
import cy.com.currenciesexample.room.CurrencyEntity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CurrencyDetailsViewModel(
    private val mCurrencyInteractor: CurrencyInteractor
) : ViewModel() {

    private val _showCurrencyDetails = MutableLiveData<CurrencyEntity>()
    val showCurrencyDetails: LiveData<CurrencyEntity> = _showCurrencyDetails

    fun showCurrencyDetails(code: String) {
        viewModelScope.launch {
            mCurrencyInteractor.fetchCurrencyDetails(code).collectLatest {
                _showCurrencyDetails.value = it
            }
        }
    }

}