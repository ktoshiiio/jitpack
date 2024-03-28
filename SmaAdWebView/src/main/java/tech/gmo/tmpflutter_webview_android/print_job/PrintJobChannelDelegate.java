package tech.gmo.tmpflutter_webview_android.print_job;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import tech.gmo.tmpflutter_webview_android.types.PrintJobInfoExt;


@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class PrintJobChannelDelegate {
  @Nullable
  private PrintJobController printJobController;

  public PrintJobChannelDelegate(@NonNull PrintJobController printJobController) {
    this.printJobController = printJobController;
  }

//  @Override
//  public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
//    switch (call.method) {
//      case "cancel":
//        if (printJobController != null) {
//          printJobController.cancel();
//          result.success(true);
//        } else {
//          result.success(false);
//        }
//        break;
//      case "restart":
//        if (printJobController != null) {
//          printJobController.restart();
//          result.success(true);
//        } else {
//          result.success(false);
//        }
//        break;
//      case "getInfo":
//        if (printJobController != null) {
//          PrintJobInfoExt info = printJobController.getInfo();
//          result.success(info != null ? info.toMap() : null);
//        } else {
//          result.success(null);
//        }
//        break;
//      case "dispose":
//        if (printJobController != null) {
//          printJobController.dispose();
//          result.success(true);
//        } else {
//          result.success(false);
//        }
//        break;
//      default:
//        result.notImplemented();
//    }
//  }

//  @Override
//  public void dispose() {
//    super.dispose();
//    printJobController = null;
//  }
}