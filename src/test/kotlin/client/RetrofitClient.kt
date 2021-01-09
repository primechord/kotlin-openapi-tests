package client

import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import configuration.creds
import configuration.logging
import io.qameta.allure.okhttp3.AllureOkHttp3
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

open class RetrofitClient {

    private var baseUrl = creds.baseUrl()
    private var apiKey = ""

    fun withApiKey(apiKey: String) = apply { this.apiKey = apiKey }
    fun withBaseUrl(baseUrl: String) = apply { this.baseUrl = baseUrl }

    fun <S> createService(serviceClass: Class<S>): S {
        val okhttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(apiKey))
            .addInterceptor(AllureOkHttp3())
        if (logging.httpLogging()) {
            okhttpClient.addInterceptor(HttpLoggingInterceptor().setLevel(logging.loggingLevel()))
        }

        val jsonMapper = JsonMapper.builder()
            .addModule(JavaTimeModule())

        return Retrofit.Builder()
            .client(okhttpClient.build())
            .addConverterFactory(JacksonConverterFactory.create(jsonMapper.build()))
            .baseUrl(baseUrl)
            .build()
            .create(serviceClass)
    }

}
