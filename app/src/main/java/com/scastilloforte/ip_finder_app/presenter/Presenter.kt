package com.scastilloforte.ip_finder_app.presenter

import com.scastilloforte.ip_finder_app.data.api.ApiModule
import com.scastilloforte.ip_finder_app.data.model.IpInfo
import com.scastilloforte.ip_finder_app.interactor.Interactor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by netx on 9/20/17.
 */
class Presenter(var view:View?) {

    var interactor:Interactor = Interactor(this, ApiModule.findIpService)
    val compositeDisposable:CompositeDisposable = CompositeDisposable()

    fun queryIp(ip: String) {
        try {
            val disposable = interactor.queryIp(ip)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe( {
                        ipInfo:IpInfo ->
                        if (view != null)
                            view!!.showResult(ipInfo)
                    }, {
                        e:Throwable ->
                        if (view != null)
                            view!!.showError("Error: " + e.message!!)
                    })

            compositeDisposable.add(disposable)
        }
        catch (e:IllegalArgumentException) {
            if (view != null)
                view!!.showError(e.message!!)
        }

    }

    fun dispose() {
        if (!compositeDisposable.isDisposed)
            compositeDisposable.dispose()
    }

    interface View {
        fun showResult(result:IpInfo)
        fun showError(error:String)
    }
}