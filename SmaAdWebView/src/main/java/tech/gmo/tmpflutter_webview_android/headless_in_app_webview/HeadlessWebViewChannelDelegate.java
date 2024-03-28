package tech.gmo.tmpflutter_webview_android.headless_in_app_webview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import tech.gmo.tmpflutter_webview_android.types.Size2D;

import java.util.HashMap;
import java.util.Map;

public class HeadlessWebViewChannelDelegate {
  @Nullable
  private HeadlessInAppWebView headlessWebView;

  public HeadlessWebViewChannelDelegate(@NonNull HeadlessInAppWebView headlessWebView) {
    this.headlessWebView = headlessWebView;
  }

//  @Override
//  public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
//    switch (call.method) {
//      case "dispose":
//        if (headlessWebView != null) {
//          headlessWebView.dispose();
//          result.success(true);
//        } else {
//          result.success(false);
//        }
//        break;
//      case "setSize":
//        if (headlessWebView != null) {
//          Map<String, Object> sizeMap = (Map<String, Object>) call.argument("size");
//          Size2D size = Size2D.fromMap(sizeMap);
//          if (size != null)
//            headlessWebView.setSize(size);
//          result.success(true);
//        } else {
//          result.success(false);
//        }
//      break;
//      case "getSize":
//        if (headlessWebView != null) {
//          Size2D size = headlessWebView.getSize();
//          result.success(size != null ? size.toMap() : null);
//        } else {
//          result.success(null);
//        }
//      break;
//      default:
//        result.notImplemented();
//    }
//  }

  public void onWebViewCreated() {
    Map<String, Object> obj = new HashMap<>();
//    channel.invokeMethod("onWebViewCreated", obj);
  }

//  @Override
//  public void dispose() {
//    super.dispose();
//    headlessWebView = null;
//  }
}
