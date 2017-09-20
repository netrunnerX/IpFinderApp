package com.scastilloforte.ip_finder_app.presenter

import com.scastilloforte.ip_finder_app.data.IpDetails
import com.scastilloforte.ip_finder_app.interactor.Interactor
import com.scastilloforte.ip_finder_app.interactor.InteractorImpl
import com.scastilloforte.ip_finder_app.view.View

/**
 * Created by netx on 9/20/17.
 */
class PresenterImpl(var view:View?) : Presenter {

    var interactor:Interactor = InteractorImpl(this)

    override fun queryIp(ip: String) {
        interactor.queryIp(ip)
    }

    override fun showResult(result: IpDetails) {
        if (view != null)
            view!!.showResult(result)
    }

    override fun showError(error: String) {
        if (view != null)
            view!!.showError(error)
    }
}