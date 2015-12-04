package kiproff.gdblogify;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    String newId = "";
    String newUrl = "";
    boolean faved = false;
    String favStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton favBtn = (ImageButton) findViewById(R.id.favBtn);
        favBtn.setVisibility(View.INVISIBLE);
        ImageButton reactBtn = (ImageButton) findViewById(R.id.reactBtn);
        reactBtn.setVisibility(View.INVISIBLE);
        ImageButton reactionsBtn = (ImageButton) findViewById(R.id.reactionsBtn);
        reactionsBtn.setVisibility(View.INVISIBLE);

        WebView view = (WebView) this.findViewById(R.id.webView);
        view.getSettings().setJavaScriptEnabled(true);

        view.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                String needle = "GDreact";

                if (url.toLowerCase().contains(needle.toLowerCase())) {

                    String[] parts = url.split("=");
                    newId = parts[1].split("&")[0];
                    newUrl = parts[2];

                    Log.d("---my id---", newId);
                    Log.d("---my newUrl---", newUrl);






                    try {
                        String msg;
                        String filePath = getFilesDir().getPath().toString() + "/favs.txt";
                        File favsFile = new File(filePath);
                        FileInputStream fileInputStream =  new FileInputStream(favsFile);
                        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        StringBuffer stringBuffer = new StringBuffer();
                        while ((msg=bufferedReader.readLine()) != null){
                            stringBuffer.append(msg);
                        }
                        favStr = stringBuffer.toString();
                        String favsArr[] = (stringBuffer.toString()).split(",");

                        for(int n = 0; n < favsArr.length; n = n+1) {
                            if(favsArr[n].equals(newId)){
                                ImageButton btn = (ImageButton) findViewById(R.id.favBtn);
                                btn.setImageResource(R.drawable.favfull);
                                faved =  true;
                            }
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }









                    ImageButton favBtn = (ImageButton) findViewById(R.id.favBtn);
                    favBtn.setVisibility(View.VISIBLE);
                    ImageButton reactBtn = (ImageButton) findViewById(R.id.reactBtn);
                    reactBtn.setVisibility(View.VISIBLE);
                    ImageButton reactionsBtn = (ImageButton) findViewById(R.id.reactionsBtn);
                    reactionsBtn.setVisibility(View.VISIBLE);
                } else {
                    ImageButton favBtn = (ImageButton) findViewById(R.id.favBtn);
                    favBtn.setVisibility(View.INVISIBLE);
                    favBtn.setImageResource(R.drawable.fav);
                    ImageButton reactBtn = (ImageButton) findViewById(R.id.reactBtn);
                    reactBtn.setVisibility(View.INVISIBLE);
                    ImageButton reactionsBtn = (ImageButton) findViewById(R.id.reactionsBtn);
                    reactionsBtn.setVisibility(View.INVISIBLE);
                    newId = "";
                    newUrl = "";
                    faved = false;
                }

                // return true; //Indicates WebView to NOT load the url;
                return false; //Allow WebView to load url
            }
        });

        view.loadUrl("http://andrewkiproff.com/gd-blogify/");

        ImageButton btn = (ImageButton) findViewById(R.id.favBtn);
        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                WebView view = (WebView) findViewById(R.id.webView);
                view.loadUrl("http://andrewkiproff.com/gd-blogify/favorites/?id="+favStr);
                return true;
            }
        });
    }

    public void favClick(View view){
        ImageButton btn = (ImageButton) findViewById(R.id.favBtn);
        if(!faved) {
            btn.setImageResource(R.drawable.favfull);
            faved =  true;
            try {
                String filePath = getFilesDir().getPath().toString() + "/favs.txt";
                File favsFile = new File(filePath);
                if(favsFile.exists() == false){ favsFile.createNewFile(); }
                String favMsg = newId+",";
                FileOutputStream fileOutputStream = new FileOutputStream(favsFile,true);
                fileOutputStream.write(favMsg.getBytes());
                fileOutputStream.close();
                Toast.makeText(getApplicationContext(),"Favorited!",Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            btn.setImageResource(R.drawable.fav);
            faved =  false;
            try {
                String msg;
                String filePath = getFilesDir().getPath().toString() + "/favs.txt";
                File favsFile = new File(filePath);
                FileInputStream fileInputStream =  new FileInputStream(favsFile);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuffer stringBuffer = new StringBuffer();
                while ((msg=bufferedReader.readLine()) != null){
                    stringBuffer.append(msg);
                }
                String favsArr[] = (stringBuffer.toString()).split(",");
                String newFavs = "";
                for(int n = 0; n < favsArr.length; n = n+1) {
                    if(!favsArr[n].equals(newId)){
                        newFavs += favsArr[n]+",";
                    }
                }
                favStr = newFavs;
                FileOutputStream fileOutputStream = new FileOutputStream(favsFile);
                fileOutputStream.write(newFavs.getBytes());
                fileOutputStream.close();

                Log.d("------------", stringBuffer.toString());

                Toast.makeText(getApplicationContext(),"Removed",Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void reactClick(View view){
        WebView myWebView = (WebView) findViewById(R.id.webView);
        myWebView.loadUrl("http://andrewkiproff.com/gd-blogify/react/?id="+newId);
    }

    public void reactionClick(View view){
        WebView myWebView = (WebView) findViewById(R.id.webView);
        myWebView.loadUrl("http://andrewkiproff.com/gd-blogify/reactions/?id="+newId);
    }
}