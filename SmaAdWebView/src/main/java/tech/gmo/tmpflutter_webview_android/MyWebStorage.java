package tech.gmo.tmpflutter_webview_android;

import android.webkit.ValueCallback;
import android.webkit.WebStorage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyWebStorage {
  protected static final String LOG_TAG = "MyWebStorage";
  public static final String METHOD_CHANNEL_NAME = "tech.gmo/tmpflutter_webview_webstoragemanager";

  @Nullable
  public static WebStorage webStorageManager;
  @Nullable
  public InAppWebViewFlutterPlugin plugin;

  public MyWebStorage(@NonNull final InAppWebViewFlutterPlugin plugin) {
    this.plugin = plugin;
  }

  public static void init() {
    if (webStorageManager == null) {
      webStorageManager = WebStorage.getInstance();
    }
  }

//  @Override
//  public void onMethodCall() {
//    init();
//
//    switch (call.method) {
//      case "getOrigins":
//        getOrigins(result);
//        break;
//      case "deleteAllData":
//        if (webStorageManager != null) {
//          webStorageManager.deleteAllData();
//          result.success(true);
//        } else {
//          result.success(false);
//        }
//        break;
//      case "deleteOrigin":
//        {
//          if (webStorageManager != null) {
//            String origin = (String) call.argument("origin");
//            webStorageManager.deleteOrigin(origin);
//            result.success(true);
//          } else {
//            result.success(false);
//          }
//        }
//        break;
//      case "getQuotaForOrigin":
//        {
//          String origin = (String) call.argument("origin");
//          getQuotaForOrigin(origin, result);
//        }
//        break;
//      case "getUsageForOrigin":
//       {
//          String origin = (String) call.argument("origin");
//          getUsageForOrigin(origin, result);
//       }
//       break;
//      default:
//        result.notImplemented();
//    }
//  }

  public void getOrigins() {
    webStorageManager.getOrigins(new ValueCallback<Map>() {
      @Override
      public void onReceiveValue(Map value) {
        List<Map<String, Object>> origins = new ArrayList<>();
        for(Object key : value.keySet()) {
          WebStorage.Origin originObj = (WebStorage.Origin) value.get(key);

          Map<String, Object> originInfo = new HashMap<>();
          originInfo.put("origin", originObj.getOrigin());
          originInfo.put("quota", originObj.getQuota());
          originInfo.put("usage", originObj.getUsage());

          origins.add(originInfo);
        }
//        result.success(origins);
      }
    });
  }

  public void getQuotaForOrigin(String origin) {
    if (webStorageManager == null) {
//      result.success(0);
      return;
    }
    webStorageManager.getQuotaForOrigin(origin, new ValueCallback<Long>() {
      @Override
      public void onReceiveValue(Long value) {

//        result.success(value);
      }
    });
  }

  public void getUsageForOrigin(String origin) {
    if (webStorageManager == null) {
//      result.success(0);
      return;
    }
    webStorageManager.getUsageForOrigin(origin, new ValueCallback<Long>() {
      @Override
      public void onReceiveValue(Long value) {

//        result.success(value);
      }
    });
  }
}
