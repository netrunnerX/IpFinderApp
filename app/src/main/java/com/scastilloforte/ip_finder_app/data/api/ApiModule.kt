package com.scastilloforte.ip_finder_app.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by net on 3/11/17.
 */
class ApiModule {

    companion object {

        var findIpService:FindIpService = getRetrofitInstance().create(FindIpService::class.java)

        private fun getRetrofitInstance():Retrofit {
            return Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(createOkHttpClient())
                    .build()
        }

        private fun createOkHttpClient():OkHttpClient {
            val httpClient:OkHttpClient.Builder = OkHttpClient.Builder()
            //Add an Interceptor that will add api key as query parameter on every request
            httpClient.addInterceptor( { chain ->
                val originalRequest = chain.request()
                val originalHttpUrl = originalRequest.url()

                val url = originalHttpUrl.newBuilder()
                        .addQueryParameter("auth", Constants.API_KEY)
                        .build()

                //Add request headers
                val requestBuilder = originalRequest.newBuilder().url(url)
                val request = requestBuilder.build()

                return@addInterceptor chain.proceed(request)
            })

            return httpClient.build()
        }
    }
}