package tech.gmo.tmpflutter_webview_android.print_job;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import tech.gmo.tmpflutter_webview_android.InAppWebViewFlutterPlugin;
import tech.gmo.tmpflutter_webview_android.types.Disposable;
import tech.gmo.tmpflutter_webview_android.types.PrintJobInfoExt;


@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class PrintJobController implements Disposable  {
  protected static final String LOG_TAG = "PrintJob";
  public static final String METHOD_CHANNEL_NAME_PREFIX = "tech.gmo/tmpflutter_webview_printjobcontroller_";
  
  @NonNull
  public String id;
  @Nullable
  public InAppWebViewFlutterPlugin plugin;
  @Nullable
  public PrintJobChannelDelegate channelDelegate;
  @Nullable
  public android.print.PrintJob job;
  @Nullable
  public PrintJobSettings settings;

  public PrintJobController(@NonNull String id, @NonNull android.print.PrintJob job,
                            @Nullable PrintJobSettings settings, @NonNull InAppWebViewFlutterPlugin plugin) {
    this.id = id;
    this.plugin = plugin;
    this.job = job;
    this.settings = settings;
    this.channelDelegate = new PrintJobChannelDelegate(this);
  }
  
  public void cancel() {
    if (this.job != null) {
      this.job.cancel();
    }
  }

  public void restart() {
    if (this.job != null) {
      this.job.restart();
    }
  }
  
  @Nullable
  public PrintJobInfoExt getInfo() {
    if (this.job != null) {
      return PrintJobInfoExt.fromPrintJobInfo(this.job.getInfo());
    }
    return null;
  }

  public void disposeNoCancel() {
    if (channelDelegate != null) {
      channelDelegate  = null;
    }
    if (plugin != null) {
      PrintJobManager printJobManager = plugin.printJobManager;
      if (printJobManager != null && printJobManager.jobs.containsKey(id)) {
        printJobManager.jobs.put(id, null);
      }
    }
    if (job != null) {
      job = null;
    }
    plugin = null;
  }
  
  @Override
  public void dispose() {
    if (channelDelegate != null) {
      channelDelegate  = null;
    }
    if (plugin != null) {
      PrintJobManager printJobManager = plugin.printJobManager;
      if (printJobManager != null && printJobManager.jobs.containsKey(id)) {
        printJobManager.jobs.put(id, null);
      }
    }
    if (job != null) {
      job.cancel();
      job = null;
    }
    plugin = null;
  }
}
