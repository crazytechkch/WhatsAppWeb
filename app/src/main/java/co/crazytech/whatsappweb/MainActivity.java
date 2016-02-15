package co.crazytech.whatsappweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWebView();
    }

    private void initWebView(){
        webView = (WebView)findViewById(R.id.webView);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        webView.setWebViewClient(new MyWebViewClient());
        webView.setWebChromeClient(new MyMebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
        String newUA= "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.4) Gecko/20100101 Chrome/41.0.2228.0 Safari/537.36";
        webView.getSettings().setUserAgentString(newUA);
        webView.loadUrl("http://web.whatsapp.com");
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private class MyMebChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if(newProgress < 100 && progressBar.getVisibility() == ProgressBar.GONE){
                progressBar.setVisibility(ProgressBar.VISIBLE);
            }
            progressBar.setProgress(newProgress);
            if(newProgress == 100) {
                progressBar.setVisibility(ProgressBar.GONE);
            }
            //super.onProgressChanged(view, newProgress);
        }
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack())webView.goBack();
        else super.onBackPressed();
    }
}
