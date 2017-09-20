package com.scastilloforte.ip_finder_app.data

import java.io.Serializable

/**
 * Created by netx on 9/18/17.
 */
data class IpDetails(var ip : String,
                     var country : String,
                     var city : String,
                     var region : String,
                     var timezone : String,
                     var latitude : Double,
                     var longitude : Double,
                     var currency : String) : Serializable