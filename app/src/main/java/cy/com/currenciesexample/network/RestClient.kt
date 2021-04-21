package cy.com.currenciesexample.network

import cy.com.currenciesexample.BuildConfig
import cy.com.currenciesexample.network.NetworkConstants.enableSSLPinning
import cy.com.currenciesexample.network.NetworkConstants.isLoggingInterceptorEnabled
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RestClient {

    val instance = createInstance()

    private fun createInstance(): ApiWebServices {
        val okHttpClientBuilder = OkHttpClient.Builder().also { builder ->
            builder.readTimeout(60, TimeUnit.SECONDS)
            builder.writeTimeout(60, TimeUnit.SECONDS)
            builder.connectTimeout(60, TimeUnit.SECONDS)
            builder.addInterceptor { chain ->
                val url = chain.request().url.newBuilder()
                    .addQueryParameter("access_key", NetworkConstants.ACCESS_KEY)
                    .build()
                val request = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .url(url)
                    .build()
                return@addInterceptor chain.proceed(request)
            }
        }
        okHttpClientBuilder.isLoggingInterceptorEnabled = BuildConfig.DEBUG
        okHttpClientBuilder.enableSSLPinning = BuildConfig.DEBUG.not()
        return Retrofit.Builder().also { builder ->
            builder.client(okHttpClientBuilder.build())
            builder.baseUrl(NetworkConstants.API_URL)
            builder.addConverterFactory(GsonConverterFactory.create())
        }.build().create(ApiWebServices::class.java)
    }
}