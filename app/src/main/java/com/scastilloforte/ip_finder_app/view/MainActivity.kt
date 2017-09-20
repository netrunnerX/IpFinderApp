package com.scastilloforte.ip_finder_app.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.scastilloforte.ip_finder_app.R
import com.scastilloforte.ip_finder_app.data.IpDetails
import com.scastilloforte.ip_finder_app.presenter.Presenter
import com.scastilloforte.ip_finder_app.presenter.PresenterImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View {

    var presenter: Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = PresenterImpl(this)

        btFindIp.setOnClickListener { findIp() }
    }

    fun findIp() {
        presenter!!.queryIp(etIp.text.toString())
    }

    override fun showResult(result: IpDetails) {
        val bundle = Bundle()
        bundle.putSerializable("ipDetails", result)

        val i = Intent(this@MainActivity, DetailsActivity::class.java)
        i.putExtras(bundle)

        startActivity(i)
    }

    override fun showError(error: String) {
        Toast.makeText(applicationContext, error, Toast.LENGTH_LONG).show()
    }

}
