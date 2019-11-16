package com.lbxtech.androidplay.activity

import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import com.lbxtech.androidplay.R
import com.lbxtech.androidplay.base.BindView

class ArticleActivity : BaseActivity() {

    @BindView(R.id.fragment_content)
    private var mainFrame: FrameLayout? = null

    private var webView: WebView? = null

    override fun getLayoutId() = R.layout.activity_article

    override fun onBindView() {
        val link = intent.getStringExtra("link")
        webView = WebView(this).apply {
            layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                    view.loadUrl(request.url.toString())
                    return true
                }
            }
        }
        mainFrame?.addView(webView)
        webView?.loadUrl(link)
    }

    override fun onBackPressed() {
        if (webView?.canGoBack() == true) {
            webView?.goBack()
            return
        }
        super.onBackPressed()
    }

    override fun onDestroy() {
        webView?.destroy()
        mainFrame?.removeAllViews()
        super.onDestroy()
    }
}
