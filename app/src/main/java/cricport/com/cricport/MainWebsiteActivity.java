package cricport.com.cricport;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainWebsiteActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        final SwipeRefreshLayout swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipeLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeLayout.setEnabled(true);
        if(CheckNetwork.isInternetAvailable(MainWebsiteActivity.this)) //returns true if internet available
        {

            webView.loadUrl("http://cricport.16mb.com");

            webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(MainWebsiteActivity.this, description, Toast.LENGTH_SHORT).show();
            }

        });      swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        webView.loadUrl("http://cricport.16mb.com");
                        swipeLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });}
        else
        {
            webView.loadUrl("file:///android_asset/errorverticle.html");
//            Toast.makeText(getActivity(),"No Internet Connection",Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onBackPressed() {
        Intent IntentBack = new Intent(MainWebsiteActivity.this, MainActivity.class);
        MainWebsiteActivity.this.startActivity(IntentBack);
        MainWebsiteActivity.this.finish();
        super.onBackPressed();
    }
}


