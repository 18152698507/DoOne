package cn.zybwz.dotask.ui.main.home

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import cn.zybwz.dotask.R
import cn.zybwz.dotask.db.AppDataBase
import cn.zybwz.dotask.db.entity.TaskEntity


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var webView: WebView
    private lateinit var selectCurrentTask: TaskEntity
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        webView = root.findViewById(R.id.webView)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    private fun initData(){
        selectCurrentTask = AppDataBase.dataBase.taskDao().getCurrentTask()
        val type = selectCurrentTask.type
        if (type==0){
            initWebView()
            webView.loadUrl(selectCurrentTask.url)
        }
    }

    private fun initWebView(){
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true
        webSettings.setSupportZoom(true)
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false

        webSettings.allowFileAccess = true
        webSettings.domStorageEnabled = true
        webSettings.databaseEnabled = true
        webSettings.setAppCacheEnabled(true)
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        webSettings.setSupportMultipleWindows(true);
        if (Build.VERSION.SDK_INT >= 21) {
            webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW; // 此句至关重要，添加后即可
        }
        webSettings.loadsImagesAutomatically = true
        webSettings.defaultTextEncodingName = "UTF-8"
        webSettings.setNeedInitialFocus(true)
        webSettings.setGeolocationEnabled(false)

        val webViewClient: WebViewClient = object : WebViewClient() {
//            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
//
//            }
            override fun onPageFinished(view: WebView, url: String) {
                Log.e("TAG", "onPageFinished: $url", )
            }
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
             Log.e("TAG", "shouldOverrideUrlLoading: $url", )
                return true
            }



            override fun onReceivedError(
                view: WebView, errorCode: Int,
                description: String, failingUrl: String
            ) {
                Log.e("TAG", "onReceivedError: $description +$failingUrl")
//                view.loadUrl("file:///android_assets/error.html")
            }
        }

        webView.webViewClient= webViewClient
        webView.webChromeClient=object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if (newProgress==100){
                    val s = view?.url ?: return
                    selectCurrentTask.url=s
                    AppDataBase.dataBase.taskDao().updateCurrentTask(selectCurrentTask)
                }
                super.onProgressChanged(view, newProgress)
            }

            override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
                Log.e("TAG", "onConsoleMessage: ")
                return super.onConsoleMessage(consoleMessage)
            }
        }
    }
}