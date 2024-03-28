package tech.gmo.tmpflutter_webview_android.in_app_browser;

import androidx.annotation.NonNull;

import tech.gmo.tmpflutter_webview_android.types.InAppBrowserMenuItem;

import java.util.HashMap;
import java.util.Map;

public class InAppBrowserChannelDelegate {
  public InAppBrowserChannelDelegate() {

  }

  public void onBrowserCreated() {
    Map<String, Object> obj = new HashMap<>();
//    channel.invokeMethod("onBrowserCreated", obj);
  }

  public void onMenuItemClicked(InAppBrowserMenuItem menuItem) {
    Map<String, Object> obj = new HashMap<>();
    obj.put("id", menuItem.getId());
//    channel.invokeMethod("onMenuItemClicked", obj);
  }

  public void onExit() {
    Map<String, Object> obj = new HashMap<>();
//    channel.invokeMethod("onExit", obj);
  }
}
