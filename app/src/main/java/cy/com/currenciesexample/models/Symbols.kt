package cy.com.currenciesexample.models

import com.google.gson.annotations.SerializedName

data class Symbols(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("symbols")
    val symbols: Map<String, String>,
    @SerializedName("error")
    val error: ErrorResponse?
)