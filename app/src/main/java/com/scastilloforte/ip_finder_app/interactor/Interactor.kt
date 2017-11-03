package com.scastilloforte.ip_finder_app.interactor

import com.scastilloforte.ip_finder_app.data.api.FindIpService
import com.scastilloforte.ip_finder_app.data.model.IpInfo
import com.scastilloforte.ip_finder_app.presenter.Presenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by netx on 9/20/17.
 */
class Interactor(var presenter: Presenter) {

    val API_KEY = "YOUR_API_KEY_HERE"
    val BASE_URL = "https://ipfind.co/"

    var findIpService: FindIpService? = null

    init {
        val retrofit:Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        findIpService = retrofit.create(FindIpService::class.java)
    }

    fun queryIp(ip: String) {
        if (ip.matches(Regex("((\\d|[1-9]\\d|1\\d{2,2}|2([0-4]\\d|5[0-5]))\\.){3}"
                + "(\\d|[1-9]\\d|1\\d{2,2}|2([0-4]\\d|5[0-5]))"))) {

            var call: Call<IpInfo> = findIpService!!.queryIp(ip, API_KEY)

            call.enqueue(object : Callback<IpInfo> {
                override fun onResponse(call: Call<IpInfo>, response: Response<IpInfo>) {
                    if (response.isSuccessful) {
                        var ipInfo:IpInfo? = response.body()

                        if (ipInfo != null) presenter.showResult(ipInfo)

                    } else {
                        presenter.showError("Response not successful")

                    }
                }

                override fun onFailure(call: Call<IpInfo>, t: Throwable) {
                    presenter.showError(t.message!!)
                }
            })
        }
        else {
            presenter.showError("Need a valid IPv4 address")
        }
    }

}