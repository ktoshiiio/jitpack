package tech.gmo.tmpflutter_webview_android.process_global_config;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.webkit.ProcessGlobalConfig;

import tech.gmo.tmpflutter_webview_android.InAppWebViewFlutterPlugin;

import java.util.Map;


public class ProcessGlobalConfigManager {
  protected static final String LOG_TAG = "ProcessGlobalConfigM";
  public static final String METHOD_CHANNEL_NAME = "tech.gmo/tmpflutter_webview_processglobalconfig";

  @Nullable
  public InAppWebViewFlutterPlugin plugin;

  public ProcessGlobalConfigManager(@NonNull final InAppWebViewFlutterPlugin plugin) {
    this.plugin = plugin;
  }

//  @Override
//  public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
//    switch (call.method) {
//      case "apply":
//        if (plugin != null && plugin.activity != null) {
//          ProcessGlobalConfigSettings settings = (new ProcessGlobalConfigSettings())
//                  .parse((Map<String, Object>) call.argument("settings"));
//          try {
//            ProcessGlobalConfig.apply(settings.toProcessGlobalConfig(plugin.activity));
//            result.success(true);
//          } catch (Exception e) {
//            result.error(LOG_TAG, "", e);
//          }
//        } else {
//          result.success(false);
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
