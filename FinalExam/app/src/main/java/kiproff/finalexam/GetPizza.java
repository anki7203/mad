package kiproff.finalexam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class GetPizza extends AppCompatActivity {

    private String pizzaUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_pizza);

        Intent intent = getIntent();
        pizzaUrl = intent.getStringExtra("pizzaUrl");

        WebView pizzaWebView = (WebView) findViewById(R.id.webView);
        pizzaWebView.loadUrl(pizzaUrl);
    }

}
