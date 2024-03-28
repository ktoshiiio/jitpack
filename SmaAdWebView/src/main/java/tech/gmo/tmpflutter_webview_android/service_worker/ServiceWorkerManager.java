package tech.gmo.tmpflutter_webview_android.service_worker;

import android.os.Build;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.webkit.ServiceWorkerClientCompat;
import androidx.webkit.ServiceWorkerControllerCompat;
import androidx.webkit.WebViewFeature;

import tech.gmo.tmpflutter_webview_android.InAppWebViewFlutterPlugin;
import tech.gmo.tmpflutter_webview_android.types.Disposable;
import tech.gmo.tmpflutter_webview_android.types.WebResourceRequestExt;
import tech.gmo.tmpflutter_webview_android.types.WebResourceResponseExt;

import java.io.ByteArrayInputStream;
import java.util.Map;

@RequiresApi(api = Build.VERSION_CODES.N)
public class ServiceWorkerManager implements Disposable {
  protected static final String LOG_TAG = "ServiceWorkerManager";
  public static final String METHOD_CHANNEL_NAME = "tech.gmo/tmpflutter_webview_serviceworkercontroller";

  @Nullable
  public ServiceWorkerChannelDelegate channelDelegate;
  @Nullable
  public static ServiceWorkerControllerCompat serviceWorkerController;
  @Nullable
  public InAppWebViewFlutterPlugin plugin;

  public ServiceWorkerManager(@NonNull final InAppWebViewFlutterPlugin plugin) {
    this.plugin = plugin;
    this.channelDelegate = new ServiceWorkerChannelDelegate(this);
  }

  public static void init() {
    if (serviceWorkerController == null &&
            WebViewFeature.isFeatureSupported(WebViewFeature.SERVICE_WORKER_BASIC_USAGE)) {
      serviceWorkerController = ServiceWorkerControllerCompat.getInstance();
    }
  }
  
  public void setServiceWorkerClient(Boolean isNull) {
    if (serviceWorkerController != null) {
      // set ServiceWorkerClient as null makes the app crashes, so just set a dummy ServiceWorkerClientCompat.
      serviceWorkerController.setServiceWorkerClient(isNull ? dummyServiceWorkerClientCompat() : new ServiceWorkerClientCompat() {
        @Nullable
        @Override
        public WebResourceResponse shouldInterceptRequest(@NonNull WebResourceRequest request) {
          WebResourceRequestExt requestExt = WebResourceRequestExt.fromWebResourceRequest(request);

          WebResourceResponseExt response = null;
          if (channelDelegate != null) {
            try {
              response = channelDelegate.shouldInterceptRequest(requestExt);
            } catch (InterruptedException e) {
              Log.e(LOG_TAG, "", e);
              return null;
            }
          }

          if (response != null) {
            String contentType = response.getContentType();
            String contentEncoding = response.getContentEncoding();
            byte[] data = response.getData();
            Map<String, String> responseHeaders = response.getHeaders();
            Integer statusCode = response.getStatusCode();
            String reasonPhrase = response.getReasonPhrase();

            ByteArrayInputStream inputStream = (data != null) ? new ByteArrayInputStream(data) : null;

            if (statusCode != null && reasonPhrase != null) {
              return new WebResourceResponse(contentType, contentEncoding, statusCode, reasonPhrase, responseHeaders, inputStream);
            } else {
              return new WebResourceResponse(contentType, contentEncoding, inputStream);
            }
          }

          return null;
        }
      }); 
    }
  }

  private ServiceWorkerClientCompat dummyServiceWorkerClientCompat() {
    return DummyServiceWorkerClientCompat.INSTANCE;
  }

  @Override
  public void dispose() {
    if (channelDelegate != null) {
      channelDelegate = null;
    }
    plugin = null;
  }

  private static final class DummyServiceWorkerClientCompat extends ServiceWorkerClientCompat {
    static final ServiceWorkerClientCompat INSTANCE = new DummyServiceWorkerClientCompat();

    @Nullable
    @Override
    public WebResourceResponse shouldInterceptRequest(@NonNull WebResourceRequest request) {
      return null;
    }
  }
}
