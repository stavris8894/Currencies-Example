package cy.com.currenciesexample.models

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("info")
    val info: String
) {
    constructor() : this(500, "", "Unknown Error")
}