package com.example.MyBrowser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var address = "https://ya.ru"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

        if (savedInstanceState != null) {
            address = savedInstanceState.getString("address", "")
        }

        goUrl()
        editTextAddress.setText(address)

        button.setOnClickListener {
            address = editTextAddress.text.toString()
            goUrl()
        }
    }

    private fun goUrl() {
        val isAddress = address.startsWith("https://") || address.startsWith("http://")
        val isShortAddress = address.contains(".ru") || address.contains(".com")
                || address.contains(".net") || address.contains(".org") || address.contains(".рф")
        when {
            isAddress -> webView.loadUrl(address)
            isShortAddress -> webView.loadUrl("https://" + address)
            else -> webView.loadUrl("https://yandex.ru/search/?text=" + address)
        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("address", address)
    }
}
