package com.scastilloforte.ip_finder_app

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    val API_KEY = "YOUR_API_KEY_HERE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btFindIp.setOnClickListener { findIp() }
    }

    fun findIp() {

        if (etIp.text.toString().matches(Regex("((\\d|[1-9]\\d|1\\d{2,2}|2([0-4]\\d|5[0-5]))\\.){3}"
                + "(\\d|[1-9]\\d|1\\d{2,2}|2([0-4]\\d|5[0-5]))"))) {

            val url = "https://ipfind.co?ip=${etIp.text}&auth=$API_KEY"

            btFindIp.isEnabled = false

            MyAsyncTask().execute(url)
        }
        else {
            etIp.error = "Need a valid IPv4 address"
        }

    }

    inner class MyAsyncTask:AsyncTask<String, String, String>() {

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
                Toast.makeText(applicationContext, result, Toast.LENGTH_LONG).show()
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

                val bundle = Bundle()
                bundle.putSerializable("ipDetails", ipDetails)

                val i = Intent(this@MainActivity, DetailsActivity::class.java)
                i.putExtras(bundle)

                startActivity(i)
            }

            btFindIp.isEnabled = true
        }
    }

    @Throws(Exception::class)
    fun fromStreamToString(inputStream : InputStream) : String {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))

        var line:String? = null
        var fullString:String = ""

        do {
            if (bufferedReader != null) {
                line = bufferedReader?.readLine()

                if (line != null)
                    fullString += line
            }
        } while (line != null)

        inputStream.close()

        return fullString
    }
}
