package com.example.dt4ce.network

import androidx.lifecycle.LiveData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.android.x.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {
    companion object {
        private const val BASE_URL = "https://dt4ce-production.up.railway.app/api/"
    }

    fun <Api> buildApi(
        api: Class<Api>,
        authToken: LiveData<String?>? = null
    ): Api {
        val clientBuilder = OkHttpClient.Builder()

        authToken?.observeForever { token ->
            if (!token.isNullOrEmpty()) {
                clientBuilder.addInterceptor { chain ->
                    chain.proceed(
                        chain.request().newBuilder().also {
                            it.addHeader("Authorization", "Bearer $token")
                        }.build()
                    )
                }
            }
        }

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            clientBuilder.addInterceptor(logging)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(clientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(api)
    }
}