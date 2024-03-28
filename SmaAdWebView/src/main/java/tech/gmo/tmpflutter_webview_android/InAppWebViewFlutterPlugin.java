package tech.gmo.tmpflutter_webview_android;

import android.app.Activity;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import tech.gmo.tmpflutter_webview_android.chrome_custom_tabs.ChromeSafariBrowserManager;
import tech.gmo.tmpflutter_webview_android.credential_database.CredentialDatabaseHandler;
import tech.gmo.tmpflutter_webview_android.headless_in_app_webview.HeadlessInAppWebViewManager;
import tech.gmo.tmpflutter_webview_android.in_app_browser.InAppBrowserManager;
import tech.gmo.tmpflutter_webview_android.print_job.PrintJobManager;
import tech.gmo.tmpflutter_webview_android.process_global_config.ProcessGlobalConfigManager;
import tech.gmo.tmpflutter_webview_android.proxy.ProxyManager;
import tech.gmo.tmpflutter_webview_android.service_worker.ServiceWorkerManager;
import tech.gmo.tmpflutter_webview_android.tracing.TracingControllerManager;
import tech.gmo.tmpflutter_webview_android.webview.FlutterWebViewFactory;
import tech.gmo.tmpflutter_webview_android.webview.InAppWebViewManager;

public class InAppWebViewFlutterPlugin {

  protected static final String LOG_TAG = "InAppWebViewFlutterPL";

  @Nullable
  public PlatformUtil platformUtil;
  @Nullable
  public InAppBrowserManager inAppBrowserManager;
  @Nullable
  public HeadlessInAppWebViewManager headlessInAppWebViewManager;
  @Nullable
  public ChromeSafariBrowserManager chromeSafariBrowserManager;
  @Nullable
  public InAppWebViewManager inAppWebViewManager;
  @Nullable
  public MyCookieManager myCookieManager;
  @Nullable
  public CredentialDatabaseHandler credentialDatabaseHandler;
  @Nullable
  public MyWebStorage myWebStorage;
  @Nullable
  public ServiceWorkerManager serviceWorkerManager;
  @Nullable
  public WebViewFeatureManager webViewFeatureManager;
  @Nullable
  public ProxyManager proxyManager;
  @Nullable
  public PrintJobManager printJobManager;
  @Nullable
  public TracingControllerManager tracingControllerManager;
  @Nullable
  public ProcessGlobalConfigManager processGlobalConfigManager;
  public FlutterWebViewFactory flutterWebViewFactory;
  public Context applicationContext;
  @Nullable
  public Activity activity;

  public InAppWebViewFlutterPlugin() {}

//  @SuppressWarnings("deprecation")
//  public static void registerWith(PluginRegistry.Registrar registrar) {
//    final InAppWebViewFlutterPlugin instance = new InAppWebViewFlutterPlugin();
//    instance.registrar = registrar;
//    instance.onAttachedToEngine(
//            registrar.context(), registrar.messenger(), registrar.activity(), registrar.platformViewRegistry(), registrar.view());
//  }

  public void OnAttachedToEngine(Activity activity) {

    // Shared.activity could be null or not.
    // It depends on who is called first between onAttachedToEngine event and onAttachedToActivity event.
    //
    onAttachedToEngine(
            activity.getApplicationContext(), activity);
  }

  @SuppressWarnings("deprecation")
  private void onAttachedToEngine(Context applicationContext, Activity activity) {
    this.applicationContext = applicationContext;
    this.activity = activity;

    inAppBrowserManager = new InAppBrowserManager(this);
    headlessInAppWebViewManager = new HeadlessInAppWebViewManager(this);
    chromeSafariBrowserManager = new ChromeSafariBrowserManager(this);
    flutterWebViewFactory = new FlutterWebViewFactory(this);

    platformUtil = new PlatformUtil(this);
    inAppWebViewManager = new InAppWebViewManager(this);
    myCookieManager = new MyCookieManager(this);
    myWebStorage = new MyWebStorage(this);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      serviceWorkerManager = new ServiceWorkerManager(this);
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      credentialDatabaseHandler = new CredentialDatabaseHandler(this);
    }
    webViewFeatureManager = new WebViewFeatureManager(this);
    proxyManager = new ProxyManager(this);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      printJobManager = new PrintJobManager(this);
    }
    tracingControllerManager = new TracingControllerManager(this);
    processGlobalConfigManager = new ProcessGlobalConfigManager(this);
  }

//  @Override
//  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
//    if (platformUtil != null) {
//      platformUtil.dispose();
//      platformUtil = null;
//    }
//    if (inAppBrowserManager != null) {
//      inAppBrowserManager.dispose();
//      inAppBrowserManager = null;
//    }
//    if (headlessInAppWebViewManager != null) {
//      headlessInAppWebViewManager.dispose();
//      headlessInAppWebViewManager = null;
//    }
//    if (chromeSafariBrowserManager != null) {
//      chromeSafariBrowserManager.dispose();
//      chromeSafariBrowserManager = null;
//    }
//    if (myCookieManager != null) {
//      myCookieManager.dispose();
//      myCookieManager = null;
//    }
//    if (myWebStorage != null) {
//      myWebStorage.dispose();
//      myWebStorage = null;
//    }
//    if (credentialDatabaseHandler != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//      credentialDatabaseHandler.dispose();
//      credentialDatabaseHandler = null;
//    }
//    if (inAppWebViewManager != null) {
//      inAppWebViewManager.dispose();
//      inAppWebViewManager = null;
//    }
//    if (serviceWorkerManager != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//      serviceWorkerManager.dispose();
//      serviceWorkerManager = null;
//    }
//    if (webViewFeatureManager != null) {
//      webViewFeatureManager.dispose();
//      webViewFeatureManager = null;
//    }
//    if (proxyManager != null) {
//      proxyManager.dispose();
//      proxyManager = null;
//    }
//    if (printJobManager != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//      printJobManager.dispose();
//      printJobManager = null;
//    }
//    if (tracingControllerManager != null) {
//      tracingControllerManager.dispose();
//      tracingControllerManager = null;
//    }
//    if (processGlobalConfigManager != null) {
//      processGlobalConfigManager.dispose();
//      processGlobalConfigManager = null;
//    }
//  }

//  @Override
//  public void onAttachedToActivity(ActivityPluginBinding activityPluginBinding) {
//    this.activityPluginBinding = activityPluginBinding;
//    this.activity = activityPluginBinding.getActivity();
//  }

//  @Override
//  public void onDetachedFromActivityForConfigChanges() {
//    this.activityPluginBinding = null;
//    this.activity = null;
//  }

//  @Override
//  public void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding) {
//    this.activityPluginBinding = activityPluginBinding;
//    this.activity = activityPluginBinding.getActivity();
//  }

//  @Override
//  public void onDetachedFromActivity() {
//    this.activityPluginBinding = null;
//    this.activity = null;
//  }
}
