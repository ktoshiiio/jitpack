package tech.gmo.tmpflutter_webview_android.types;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public interface ICallbackResult<T> {
  boolean nonNullSuccess(@NonNull T result);
  boolean nullSuccess();
  void defaultBehaviour(@Nullable T result);
  @Nullable T decodeResult(@Nullable Object obj);
}
