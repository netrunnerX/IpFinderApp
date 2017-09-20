package com.scastilloforte.ip_finder_app.presenter

import com.scastilloforte.ip_finder_app.data.IpDetails

/**
 * Created by netx on 9/20/17.
 */
interface Presenter {
    fun queryIp(ip:String)
    fun showResult(result: IpDetails)
    fun showError(error:String)
}