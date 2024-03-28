package tech.gmo.tmpflutter_webview_android.tracing;

import androidx.annotation.Nullable;
import androidx.webkit.TracingConfig;
import androidx.webkit.TracingController;
import androidx.webkit.WebViewFeature;

import tech.gmo.tmpflutter_webview_android.InAppWebViewFlutterPlugin;
import tech.gmo.tmpflutter_webview_android.types.Disposable;


public class TracingControllerManager implements Disposable {
  protected static final String LOG_TAG = "TracingControllerMan";
  public static final String METHOD_CHANNEL_NAME = "tech.gmo/tmpflutter_webview_tracingcontroller";

  @Nullable
  public TracingControllerChannelDelegate channelDelegate;
  @Nullable
  public static TracingController tracingController;
  @Nullable
  public InAppWebViewFlutterPlugin plugin;

  public TracingControllerManager(final InAppWebViewFlutterPlugin plugin) {
    this.plugin = plugin;
    this.channelDelegate = new TracingControllerChannelDelegate(this);
  }

  public static void init() {
    if (tracingController == null &&
            WebViewFeature.isFeatureSupported(WebViewFeature.TRACING_CONTROLLER_BASIC_USAGE)) {
      tracingController = TracingController.getInstance();
    }
  }

  public static TracingConfig buildTracingConfig(TracingSettings settings) {
    TracingConfig.Builder builder = new TracingConfig.Builder();
    for (Object category : settings.categories) {
      if (category instanceof String) {
        builder.addCategories((String) category);
      }
      if (category instanceof Integer) {
        builder.addCategories((Integer) category);
      }
    }
    if (settings.tracingMode != null) {
      builder.setTracingMode(settings.tracingMode);
    }
    return builder.build();
  }

  @Override
  public void dispose() {
    if (channelDelegate != null) {
      channelDelegate = null;
    }
    plugin = null;
  }
}
