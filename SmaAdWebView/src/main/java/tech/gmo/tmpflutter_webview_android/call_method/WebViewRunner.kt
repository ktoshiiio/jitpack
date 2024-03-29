import android.app.Activity
import tech.gmo.tmpflutter_webview_android.InAppWebViewFlutterPlugin
import tech.gmo.tmpflutter_webview_android.headless_in_app_webview.HeadlessInAppWebViewManager

object WebViewRunner {

    fun runWebView(id: String, activity: Activity) {
        val plugin = InAppWebViewFlutterPlugin()
        plugin.OnAttachedToEngine(activity)

        val webViewManager = plugin.headlessInAppWebViewManager ?: HeadlessInAppWebViewManager(plugin).also {
            plugin.headlessInAppWebViewManager = it
        }

//        val params = hashMapOf<String, Any>(
//            "initialUrlRequest" to mapOf("url" to "https://offerwall.stg.smaad.net/wall/560363030?u=test1234"),
//            "initialFile" to "file/path",
//            "initialData" to mapOf("dataKey" to "dataValue"),
//            // その他のパラメータを適宜設定
//        )
        val params = hashMapOf<String, Any>(
            "initialUrlRequest" to mapOf("url" to "https://offerwall.stg.smaad.net/wall/560363030?u=test1234"),
            // その他のパラメータを適宜設定
        )

        webViewManager.run(id, params)
    }
}
