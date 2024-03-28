package tech.gmo.tmpflutter_webview_android.find_interaction;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import tech.gmo.tmpflutter_webview_android.types.FindSession;

import java.util.HashMap;
import java.util.Map;

public class FindInteractionChannelDelegate {
  @Nullable
  private FindInteractionController findInteractionController;

  public FindInteractionChannelDelegate(@NonNull FindInteractionController findInteractionController) {
    this.findInteractionController = findInteractionController;
  }

//  @Override
//  public void onMethodCall() {
//    switch (call.method) {
//      case "findAll":
//        if (findInteractionController != null) {
//          String find = (String) call.argument("find");
//          findInteractionController.findAll(find);
//        }
//        result.success(true);
//        break;
//      case "findNext":
//        if (findInteractionController != null) {
//          Boolean forward = (Boolean) call.argument("forward");
//          findInteractionController.findNext(forward);
//        }
//        result.success(true);
//        break;
//      case "clearMatches":
//        if (findInteractionController != null) {
//          findInteractionController.clearMatches();
//        }
//        result.success(true);
//        break;
//      case "setSearchText":
//        if (findInteractionController != null) {
//          findInteractionController.searchText = (String) call.argument("searchText");
//          result.success(true);
//        } else {
//          result.success(false);
//        }
//        break;
//      case "getSearchText":
//        if (findInteractionController != null) {
//          result.success(findInteractionController.searchText);
//        } else {
//          result.success(false);
//        }
//        break;
//      case "getActiveFindSession":
//        if (findInteractionController != null && findInteractionController.activeFindSession != null) {
//          result.success(findInteractionController.activeFindSession.toMap());
//        } else {
//          result.success(null);
//        }
//        break;
//      default:
//        result.notImplemented();
//    }
//  }

  public void onFindResultReceived(int activeMatchOrdinal, int numberOfMatches, boolean isDoneCounting) {

    if (isDoneCounting && findInteractionController != null && findInteractionController.webView != null) {
      findInteractionController.activeFindSession = new FindSession(numberOfMatches, activeMatchOrdinal);
    }

    Map<String, Object> obj = new HashMap<>();
    obj.put("activeMatchOrdinal", activeMatchOrdinal);
    obj.put("numberOfMatches", numberOfMatches);
    obj.put("isDoneCounting", isDoneCounting);
//    channel.invokeMethod("onFindResultReceived", obj);
  }

//  @Override
//  public void dispose() {
//    super.dispose();
//    findInteractionController = null;
//  }
}
