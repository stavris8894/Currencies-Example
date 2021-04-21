package cy.com.currenciesexample.currencies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import cy.com.currenciesexample.R
import cy.com.currenciesexample.currencies.ui.CurrencyDetailsDialogFragment
import cy.com.currenciesexample.currencies.viewmodel.CurrenciesViewModel
import cy.com.currenciesexample.databinding.ActivityMainBinding
import cy.com.currenciesexample.ui_data.CurrencyUIViewData
import cy.com.currenciesexample.utils.interfaces.ViewHolderId
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mViewModel: CurrenciesViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

    private val mCurrencyAdapter = CurrencyAdapter().apply {
        listener = { item ->
            item as CurrencyUIViewData<out ViewHolderId>
            CurrencyDetailsDialogFragment.newInstance(item.data.currencyCode)
                .show(supportFragmentManager, CurrencyDetailsDialogFragment.TAG)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureObservers()
        configureElements()
    }

    private fun configureObservers() {
        mViewModel.data.observe(this, {
            mCurrencyAdapter.submitList(it)
            binding.swipeRefreshLayout.isRefreshing = false
        })
        mViewModel.errorResponseLiveData.observe(this, {
            MaterialAlertDialogBuilder(this)
                .setTitle(R.string.error)
                .setMessage(it.info)
                .setPositiveButton(R.string.ok) { _, _ ->

                }.show()
            binding.swipeRefreshLayout.isRefreshing = false
        })
    }

    private fun configureElements() {
        binding.recyclerView.apply {
            adapter = mCurrencyAdapter
            layoutManager = LinearLayoutManager(context)
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            mViewModel.fetchData()
        }
    }


}