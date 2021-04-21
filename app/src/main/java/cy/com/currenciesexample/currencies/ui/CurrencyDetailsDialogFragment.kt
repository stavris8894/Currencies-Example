package cy.com.currenciesexample.currencies.ui

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import cy.com.currenciesexample.R
import cy.com.currenciesexample.currencies.viewmodel.CurrencyDetailsViewModel
import cy.com.currenciesexample.databinding.CurrencyDetailsFragmentBinding
import cy.com.currenciesexample.utils.viewBinding
import cy.com.currenciesexample.utils.viewholders.toFlagUrl
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrencyDetailsDialogFragment(
    private val currencyCode: String
) : DialogFragment(R.layout.currency_details_fragment) {

    private val binding by viewBinding(CurrencyDetailsFragmentBinding::bind)

    private val mViewModel: CurrencyDetailsViewModel by viewModel()

    companion object {
        val TAG: String = CurrencyDetailsDialogFragment::class.java.simpleName

        fun newInstance(
            currencyCode: String
        ): CurrencyDetailsDialogFragment = CurrencyDetailsDialogFragment(currencyCode)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureElements()
        configureObservers()
    }

    private fun configureElements() {
        binding.closeButton.apply {
            setOnClickListener {
                dismiss()
            }
        }
    }

    private fun configureObservers() {
        mViewModel.showCurrencyDetails.observe(viewLifecycleOwner, {
            binding.apply {
                Picasso.get()
                    .load(it.currencyCode.toFlagUrl(112, 84))
                    .error(R.drawable.outline_info_black_36)
                    .placeholder(R.drawable.outline_info_black_36)
                    .into(flagImageView)
                currencyName.text = "${it.currencyDescription} (${it.currencyCode})"
                currencyRate.text = getString(R.string.rates, it.currencyRate.toString())
            }
        })
        mViewModel.showCurrencyDetails(currencyCode)
    }

    override fun onResume() {
        super.onResume()
        adjustDialogSize()
    }

    private fun adjustDialogSize() {
        dialog?.window.let {
            val displayMetrics = DisplayMetrics()
            it?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
            val marginTopBottom =
                resources.getDimensionPixelSize(R.dimen.dialogMarginTopAndBottom) * 4
            val marginSide = resources.getDimensionPixelSize(R.dimen.dialogSideMargin) * 2
            val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
            params.width = displayMetrics.widthPixels - marginSide
            params.height = displayMetrics.heightPixels - marginTopBottom
            it?.attributes = params as WindowManager.LayoutParams
        }
    }
}