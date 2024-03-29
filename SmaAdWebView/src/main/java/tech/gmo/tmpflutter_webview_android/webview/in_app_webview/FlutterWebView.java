package tech.gmo.tmpflutter_webview_android.webview.in_app_webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.webkit.WebViewFeature;

import tech.gmo.tmpflutter_webview_android.InAppWebViewFlutterPlugin;
import tech.gmo.tmpflutter_webview_android.find_interaction.FindInteractionController;
import tech.gmo.tmpflutter_webview_android.webview.PlatformWebView;
import tech.gmo.tmpflutter_webview_android.types.URLRequest;
import tech.gmo.tmpflutter_webview_android.types.UserScript;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlutterWebView implements PlatformWebView {

  static final String LOG_TAG = "IAWFlutterWebView";

  @Nullable
  public InAppWebView webView;
  @Nullable
  public String keepAliveId;

  public FlutterWebView(final InAppWebViewFlutterPlugin plugin, final Context context, Object id,
                        HashMap<String, Object> params) {
    DisplayListenerProxy displayListenerProxy = new DisplayListenerProxy();
    DisplayManager displayManager = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
    displayListenerProxy.onPreWebViewInitialization(displayManager);

    keepAliveId = (String) params.get("keepAliveId");
    
    Map<String, Object> initialSettings = (Map<String, Object>) params.get("initialSettings");
    if (initialSettings == null) {
      initialSettings = new HashMap<String, Object>(); // 空のマップをデフォルト値として設定
    }
    Map<String, Object> contextMenu = (Map<String, Object>) params.get("contextMenu");
    Integer windowId = (Integer) params.get("windowId");
    List<Map<String, Object>> initialUserScripts = (List<Map<String, Object>>) params.get("initialUserScripts");

    InAppWebViewSettings customSettings = new InAppWebViewSettings();
    customSettings.parse(initialSettings);

    List<UserScript> userScripts = new ArrayList<>();
    if (initialUserScripts != null) {
      for (Map<String, Object> initialUserScript : initialUserScripts) {
        userScripts.add(UserScript.fromMap(initialUserScript));
      }
    }

    webView = new InAppWebView(context, plugin, id, windowId, customSettings, contextMenu,
            customSettings.useHybridComposition ? null : null, userScripts);
    displayListenerProxy.onPostWebViewInitialization(displayManager);

    FindInteractionController findInteractionController = new FindInteractionController(webView, plugin, id, null);
    webView.findInteractionController = findInteractionController;
    findInteractionController.prepare();

    webView.prepare();
  }

  @SuppressLint("RestrictedApi")
  public void makeInitialLoad(HashMap<String, Object> params) {
    if (webView == null) {
      return;
    }

    Integer windowId = (Integer) params.get("windowId");
    Map<String, Object> initialUrlRequest = (Map<String, Object>) params.get("initialUrlRequest");
    final String initialFile = (String) params.get("initialFile");
    final Map<String, String> initialData = (Map<String, String>) params.get("initialData");

    if (windowId != null) {
      if (webView.plugin != null && webView.plugin.inAppWebViewManager != null) {
        Message resultMsg = webView.plugin.inAppWebViewManager.windowWebViewMessages.get(windowId);
        if (resultMsg != null) {
          ((WebView.WebViewTransport) resultMsg.obj).setWebView(webView);
          resultMsg.sendToTarget();
          if (WebViewFeature.isFeatureSupported(WebViewFeature.DOCUMENT_START_SCRIPT)) {
            // for some reason, if a WebView is created using a window id,
            // the initial plugin and user scripts injected
            // with WebViewCompat.addDocumentStartJavaScript will not be added!
            //
            // Also, calling the prepareAndAddUserScripts method right after won't work,
            // so use the View.post method here.
            webView.post(new Runnable() {
              @Override
              public void run() {
                if (webView != null) {
                  webView.prepareAndAddUserScripts();
                }
              }
            });
          }
        }
      }
    } else {
      if (initialFile != null) {
        try {
          webView.loadFile(initialFile);
        } catch (IOException e) {
          Log.e(LOG_TAG, initialFile + " asset file cannot be found!", e);
        }
      }
      else if (initialData != null) {
        String data = initialData.get("data");
        String mimeType = initialData.get("mimeType");
        String encoding = initialData.get("encoding");
        String baseUrl = initialData.get("baseUrl");
        String historyUrl = initialData.get("historyUrl");
        webView.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl);
      }
      else if (initialUrlRequest != null) {
        URLRequest urlRequest = URLRequest.fromMap(initialUrlRequest);
        if (urlRequest != null) {
          webView.loadUrl(urlRequest);
        }
      }
    }
  }

//  @Override
//  public void dispose() {
//    if (keepAliveId == null && webView != null) {
//      webView.dispose();
//      webView = null;
//
//      if (pullToRefreshLayout != null) {
//        pullToRefreshLayout.dispose();
//        pullToRefreshLayout = null;
//      }
//    }
//  }

//  @Override
//  public void onInputConnectionLocked() {
//    if (webView != null && webView.inAppBrowserDelegate == null && !webView.customSettings.useHybridComposition)
//      webView.lockInputConnection();
//  }

//  @Override
//  public void onInputConnectionUnlocked() {
//    if (webView != null && webView.inAppBrowserDelegate == null && !webView.customSettings.useHybridComposition)
//      webView.unlockInputConnection();
//  }

//  @Override
//  public void onFlutterViewAttached(@NonNull View flutterView) {
//    if (webView != null && !webView.customSettings.useHybridComposition) {
//      webView.setContainerView(flutterView);
//    }
//  }

//  @Override
//  public void onFlutterViewDetached() {
//    if (webView != null && !webView.customSettings.useHybridComposition) {
//      webView.setContainerView(null);
//    }
//  }
}