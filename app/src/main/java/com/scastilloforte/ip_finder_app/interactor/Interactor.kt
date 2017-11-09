package com.scastilloforte.ip_finder_app.interactor

import com.scastilloforte.ip_finder_app.data.api.FindIpService
import com.scastilloforte.ip_finder_app.data.model.IpInfo
import com.scastilloforte.ip_finder_app.presenter.Presenter
import io.reactivex.Observable

/**
 * Created by netx on 9/20/17.
 */
class Interactor(var findIpService: FindIpService) {

    @Throws(IllegalArgumentException::class)
    fun queryIp(ip: String): Observable<IpInfo> {
        if (ip.matches(Regex("((\\d|[1-9]\\d|1\\d{2,2}|2([0-4]\\d|5[0-5]))\\.){3}"
                + "(\\d|[1-9]\\d|1\\d{2,2}|2([0-4]\\d|5[0-5]))"))) {

            return findIpService.queryIp(ip)
        }
        else {
            throw IllegalArgumentException("ip no valida")
        }
    }

}