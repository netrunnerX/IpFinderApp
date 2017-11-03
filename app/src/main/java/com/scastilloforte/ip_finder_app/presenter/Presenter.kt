package com.scastilloforte.ip_finder_app.presenter

import com.scastilloforte.ip_finder_app.data.model.IpInfo
import com.scastilloforte.ip_finder_app.interactor.Interactor

/**
 * Created by netx on 9/20/17.
 */
class Presenter(var view:View?) {

    var interactor:Interactor = Interactor(this)

    fun queryIp(ip: String) {
        interactor.queryIp(ip)
    }

    fun showResult(result: IpInfo) {
        if (view != null)
            view!!.showResult(result)
    }

    fun showError(error: String) {
        if (view != null)
            view!!.showError(error)
    }

    interface View {
        fun showResult(result:IpInfo)
        fun showError(error:String)
    }
}