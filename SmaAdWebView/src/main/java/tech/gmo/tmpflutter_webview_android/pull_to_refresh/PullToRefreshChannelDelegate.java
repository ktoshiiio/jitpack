package tech.gmo.tmpflutter_webview_android.pull_to_refresh;

import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.HashMap;
import java.util.Map;


public class PullToRefreshChannelDelegate {
  @Nullable
  private PullToRefreshLayout pullToRefreshView;

  public PullToRefreshChannelDelegate(@NonNull PullToRefreshLayout pullToRefreshView) {
    this.pullToRefreshView = pullToRefreshView;
  }

//  @Override
//  public void onMethodCall() {
//    switch (call.method) {
//      case "setEnabled":
//        if (pullToRefreshView != null) {
//          Boolean enabled = (Boolean) call.argument("enabled");
//          pullToRefreshView.settings.enabled = enabled; // used by InAppWebView.onOverScrolled
//          pullToRefreshView.setEnabled(enabled);
//          result.success(true);
//        } else {
//          result.success(false);
//        }
//        break;
//      case "isEnabled":
//        if (pullToRefreshView != null) {
//          result.success(pullToRefreshView.isEnabled());
//        } else {
//          result.success(false);
//        }
//        break;
//      case "setRefreshing":
//        if (pullToRefreshView != null) {
//          Boolean refreshing = (Boolean) call.argument("refreshing");
//          pullToRefreshView.setRefreshing(refreshing);
//          result.success(true);
//        } else {
//          result.success(false);
//        }
//        break;
//      case "isRefreshing":
//        result.success(pullToRefreshView != null && pullToRefreshView.isRefreshing());
//        break;
//      case "setColor":
//        if (pullToRefreshView != null) {
//          String color = (String) call.argument("color");
//          pullToRefreshView.setColorSchemeColors(Color.parseColor(color));
//          result.success(true);
//        } else {
//          result.success(false);
//        }
//        break;
//      case "setBackgroundColor":
//        if (pullToRefreshView != null) {
//          String color = (String) call.argument("color");
//          pullToRefreshView.setProgressBackgroundColorSchemeColor(Color.parseColor(color));
//          result.success(true);
//        } else {
//          result.success(false);
//        }
//        break;
//      case "setDistanceToTriggerSync":
//        if (pullToRefreshView != null) {
//          Integer distanceToTriggerSync = (Integer) call.argument("distanceToTriggerSync");
//          pullToRefreshView.setDistanceToTriggerSync(distanceToTriggerSync);
//          result.success(true);
//        } else {
//          result.success(false);
//        }
//        break;
//      case "setSlingshotDistance":
//        if (pullToRefreshView != null) {
//          Integer slingshotDistance = (Integer) call.argument("slingshotDistance");
//          pullToRefreshView.setSlingshotDistance(slingshotDistance);
//          result.success(true);
//        } else {
//          result.success(false);
//        }
//        break;
//      case "getDefaultSlingshotDistance":
//        result.success(SwipeRefreshLayout.DEFAULT_SLINGSHOT_DISTANCE);
//        break;
//      case "setSize":
//        if (pullToRefreshView != null) {
//          Integer size = (Integer) call.argument("size");
//          pullToRefreshView.setSize(size);
//          result.success(true);
//        } else {
//          result.success(false);
//        }
//        break;
//      default:
//        result.notImplemented();
//    }
//  }

  public void onRefresh() {
    Map<String, Object> obj = new HashMap<>();
//    channel.invokeMethod("onRefresh", obj);
  }

//  @Override
//  public void dispose() {
//    super.dispose();
//    pullToRefreshView = null;
//  }
}
