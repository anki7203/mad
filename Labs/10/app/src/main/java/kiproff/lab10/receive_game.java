package kiproff.lab10;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;


import org.w3c.dom.Text;

public class receive_game extends AppCompatActivity {

    private String gameTitle;
    private String gameUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_game);

        Intent intent = getIntent();
        gameTitle = intent.getStringExtra("gameTitle");
        gameUrl = intent.getStringExtra("gameUrl");
        System.out.println(gameTitle);
        System.out.println(gameUrl);

        TextView messageView = (TextView) findViewById(R.id.gameTitleTextView);
        messageView.setText("You should check out " + gameTitle);

        WebView myWebView = (WebView) findViewById(R.id.webView);
        myWebView.setVisibility(View.GONE);
    }

    public void loadWebSite(View view){
        WebView myWebView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setVisibility(View.VISIBLE);
        myWebView.loadUrl(gameUrl);
        //setContentView(myWebView);

        //Intent intent = new Intent(Intent.ACTION_VIEW);
        //intent.setData(Uri.parse(gameUrl));
    }
}
