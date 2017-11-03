package com.scastilloforte.ip_finder_app.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by net on 3/11/17.
 */
class ApiModule {

    companion object {

        val BASE_URL = "https://ipfind.co/"

        var findIpService:FindIpService = getRetrofitInstance().create(FindIpService::class.java)

        private fun getRetrofitInstance():Retrofit {
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }
}