package com.scastilloforte.ip_finder_app.data.api

import com.scastilloforte.ip_finder_app.data.model.IpInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by net on 3/11/17.
 */
interface FindIpService {

    @GET(".")
    fun queryIp(@Query("ip") ip:String, @Query("auth") apiKey:String): Call<IpInfo>
}