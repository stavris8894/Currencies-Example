package cy.com.currenciesexample.network

import com.datatheorem.android.trustkit.TrustKit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.net.URL

object NetworkConstants {

    const val API_URL = "http://data.fixer.io/api/"
    const val ACCESS_KEY = "a8384217b73caedf1ccdb3a01aff7fe3d"

    //extension to enable debug http logging
    var OkHttpClient.Builder.isLoggingInterceptorEnabled: Boolean
        get() = interceptors().find { it is HttpLoggingInterceptor } != null
        set(value) {
            if (value) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(loggingInterceptor)
            }
        }

    //extension to enable ssl pinning with trust kit
    var OkHttpClient.Builder.enableSSLPinning: Boolean
        get() = false
        set(value) {
            if (value) {
                try {
                    val hostName = URL(API_URL).host
                    sslSocketFactory(
                        TrustKit.getInstance().getSSLSocketFactory(hostName),
                        TrustKit.getInstance().getTrustManager(hostName)
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

}