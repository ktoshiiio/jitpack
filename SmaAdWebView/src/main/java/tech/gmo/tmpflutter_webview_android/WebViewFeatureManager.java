package tech.gmo.tmpflutter_webview_android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WebViewFeatureManager {
  protected static final String LOG_TAG = "WebViewFeatureManager";
  public static final String METHOD_CHANNEL_NAME = "tech.gmo/tmpflutter_webview_webviewfeature";

  @Nullable
  public InAppWebViewFlutterPlugin plugin;

  public WebViewFeatureManager(@NonNull final InAppWebViewFlutterPlugin plugin) {
    this.plugin = plugin;
  }

//  @Override
//  public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
//    switch (call.method) {
//      case "isFeatureSupported":
//        String feature = (String) call.argument("feature");
//        result.success(WebViewFeature.isFeatureSupported(feature));
//        break;
//      case "isStartupFeatureSupported":
//        if (plugin != null && plugin.activity != null) {
//          String startupFeature = (String) call.argument("startupFeature");
//          result.success(WebViewFeature.isStartupFeatureSupported(plugin.activity, startupFeature));
//        }
//        break;
//      default:
//        result.notImplemented();
//    }
//  }

//  @Override
//  public void dispose() {
//    super.dispose();
//    plugin = null;
//  }
}
