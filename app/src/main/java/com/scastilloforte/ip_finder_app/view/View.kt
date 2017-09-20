package com.scastilloforte.ip_finder_app.view

import com.scastilloforte.ip_finder_app.data.IpDetails

/**
 * Created by netx on 9/20/17.
 */
interface View {
    fun showResult(result:IpDetails)
    fun showError(error:String)
}