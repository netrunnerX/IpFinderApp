package com.scastilloforte.ip_finder_app.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.scastilloforte.ip_finder_app.R
import com.scastilloforte.ip_finder_app.data.model.IpInfo
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val ipInfo = intent.extras.getSerializable("ipDetails") as IpInfo

        tvIp.text = ipInfo.ipAddress
        tvCountry.text = ipInfo.country
        tvCity.text = ipInfo.city
        tvRegion.text = ipInfo.region
        tvTimeZone.text = ipInfo.timezone
        tvCurrency.text = ipInfo.currency
        tvLatitude.text = ipInfo.latitude.toString()
        tvLongitude.text = ipInfo.longitude.toString()

    }
}
