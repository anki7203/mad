package kiproff.gdblogify;

import android.webkit.WebView;

/**
 * Created by Andrew on 12/2/2015.
 */
public interface GDBrowserOverride {
    boolean ShouldOverrideUrlLoading(WebView view, String Url);
}
