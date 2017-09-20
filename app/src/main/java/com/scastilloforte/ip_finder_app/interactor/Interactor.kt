package com.scastilloforte.ip_finder_app.interactor

import android.os.AsyncTask
import com.scastilloforte.ip_finder_app.data.IpDetails
import com.scastilloforte.ip_finder_app.presenter.Presenter
import org.json.JSONObject
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by netx on 9/20/17.
 */
class Interactor(var presenter: Presenter) {

    val API_KEY = "YOUR_API_KEY_HERE"

    fun queryIp(ip: String) {
        if (ip.matches(Regex("((\\d|[1-9]\\d|1\\d{2,2}|2([0-4]\\d|5[0-5]))\\.){3}"
                + "(\\d|[1-9]\\d|1\\d{2,2}|2([0-4]\\d|5[0-5]))"))) {

            val url = "https://ipfind.co?ip=$ip&auth=$API_KEY"

            MyAsyncTask().execute(url)
        }
        else {
            presenter.showError("Need a valid IPv4 address")
        }
    }

    inner class MyAsyncTask: AsyncTask<String, String, String>() {

        override fun doInBackground(vararg params: String?): String {
            var stringResponse : String
            try {
                var url = URL(params[0])

                val urlConnection = url.openConnection() as HttpURLConnection

                urlConnection.connectTimeout = 6999

                stringResponse = fromStreamToString(urlConnection.inputStream)
            }
            catch (e : FileNotFoundException) {
                stringResponse = "URL Error: Check that the IP and the api key are valid"
            }
            catch (e : Exception) {
                stringResponse = "Error: ${e.message}"
            }

            return stringResponse
        }

        override fun onPostExecute(result: String?) {
            if (result!!.contains("Error")) {
                presenter.showError(result)
            }
            else {
                var json = JSONObject(result)
                val ip = json.getString("ip_address")
                val country = json.getString("country")
                val city = json.getString("city")
                val region = json.getString("region")
                val timezone = json.getString("timezone")
                val latitude = json.getDouble("latitude")
                val longitude = json.getDouble("longitude")
                val currency = json.getString("currency")

                val ipDetails =
                        IpDetails(ip, country, city, region, timezone, latitude, longitude, currency)

                presenter.showResult(ipDetails)
            }

        }
    }

    fun fromStreamToString(inputStream : InputStream) : String {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))

        var line:String? = null
        var fullString:String = ""

        try {
            do {
                if (bufferedReader != null) {
                    line = bufferedReader?.readLine()

                    if (line != null)
                        fullString += line
                }
            } while (line != null)

            inputStream.close()
        }
        catch (e:Exception) {
            fullString = "Error: ${e.message}"
        }

        return fullString
    }
}