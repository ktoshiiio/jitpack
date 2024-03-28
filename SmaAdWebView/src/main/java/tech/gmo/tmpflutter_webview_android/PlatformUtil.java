package tech.gmo.tmpflutter_webview_android;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class PlatformUtil {
  protected static final String LOG_TAG = "PlatformUtil";
  public static final String METHOD_CHANNEL_NAME = "tech.gmo/tmpflutter_webview_platformutil";
  
  @Nullable
  public InAppWebViewFlutterPlugin plugin;

  public PlatformUtil(final InAppWebViewFlutterPlugin plugin) {
    this.plugin = plugin;
  }

//  @Override
//  public void onMethodCall() {
//    switch (call.method) {
//      case "getSystemVersion":
//        result.success(String.valueOf(Build.VERSION.SDK_INT));
//        break;
//      case "formatDate":
//        long date = (long) call.argument("date");
//        String format = (String) call.argument("format");
//        Locale locale = PlatformUtil.getLocaleFromString((String) call.argument("locale"));
//        String timezone = (String) call.argument("timezone");
//        if (timezone == null) {
//          timezone = "UTC";
//        }
//        result.success(PlatformUtil.formatDate(date, format, locale, TimeZone.getTimeZone(timezone)));
//        break;
//      default:
//        result.notImplemented();
//    }
//  }

  public static Locale getLocaleFromString(@Nullable String locale) {
    if (locale == null) {
      return Locale.US;
    }
    String[] localeSplitted = locale.split("_");
    String language = localeSplitted[0];
    String country = localeSplitted.length > 1 ? localeSplitted[1] : "";
    String variant = localeSplitted.length > 2 ? localeSplitted[2] : "";
    return new Locale(language, country, variant);
  }

  public static String formatDate(long date, String format, Locale locale, TimeZone timezone) {
    final SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
    sdf.setTimeZone(timezone);
    return sdf.format(new Date(date));
  }

}
