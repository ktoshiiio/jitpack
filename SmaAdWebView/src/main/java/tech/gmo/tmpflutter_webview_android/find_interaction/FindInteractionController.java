package tech.gmo.tmpflutter_webview_android.find_interaction;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import tech.gmo.tmpflutter_webview_android.InAppWebViewFlutterPlugin;
import tech.gmo.tmpflutter_webview_android.types.Disposable;
import tech.gmo.tmpflutter_webview_android.types.FindSession;
import tech.gmo.tmpflutter_webview_android.webview.InAppWebViewInterface;

public class FindInteractionController implements Disposable {
  static final String LOG_TAG = "FindInteractionController";
  public static final String METHOD_CHANNEL_NAME_PREFIX = "tech.gmo/tmpflutter_webview_find_interaction_";

  @Nullable
  public InAppWebViewInterface webView;
  @Nullable
  public FindSession activeFindSession;
  @Nullable
  public FindInteractionChannelDelegate channelDelegate;
  @Nullable
  public FindInteractionSettings settings;
  @Nullable
  public String searchText;

  public FindInteractionController(@NonNull InAppWebViewInterface webView, @NonNull InAppWebViewFlutterPlugin plugin,
                             @NonNull Object id, @Nullable FindInteractionSettings settings) {
    this.webView = webView;
    this.settings = settings;
    this.channelDelegate = new FindInteractionChannelDelegate(this);
  }

  public void prepare() {

  }

  public void findAll(@Nullable String find) {
    if (find == null) {
      find = searchText;
    } else {
      // updated searchText
      searchText = find;
    }
    if (webView != null && find != null) {
      webView.findAllAsync(find);
    }
  }

  public void findNext(boolean forward) {
    if (webView != null) {
      webView.findNext(forward);
    }
  }

  public void clearMatches() {
    if (webView != null) {
      webView.clearMatches();
    }
  }

  public void dispose() {
    if (channelDelegate != null) {
      channelDelegate = null;
    }
    webView = null;
    activeFindSession = null;
    searchText = null;
  }
}
