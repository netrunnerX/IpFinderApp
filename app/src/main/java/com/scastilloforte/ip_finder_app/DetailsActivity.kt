package com.scastilloforte.ip_finder_app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val ipDetails = intent.extras.getSerializable("ipDetails") as IpDetails

        tvIp.text = ipDetails.ip
        tvCountry.text = ipDetails.country
        tvCity.text = ipDetails.city
        tvRegion.text = ipDetails.region
        tvTimeZone.text = ipDetails.timezone
        tvCurrency.text = ipDetails.currency
        tvLatitude.text = ipDetails.latitude.toString()
        tvLongitude.text = ipDetails.longitude.toString()

    }
}
