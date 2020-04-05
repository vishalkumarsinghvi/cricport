package cricport.com.cricport;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LiveStreamingActivity extends AppCompatActivity {
    private WebView webView;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        webView = (WebView) findViewById(R.id.webView1);
//        final SwipeRefreshLayout swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
//        swipeLayout.setColorScheme(android.R.color.holo_blue_bright,
//                android.R.color.holo_green_light,
//                android.R.color.holo_orange_light,
//                android.R.color.holo_red_light);
//        swipeLayout.setEnabled(true);
        mProgress = ProgressDialog.show(LiveStreamingActivity.this, "Loading", "Please wait for a moment...");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.scrollTo(0,350);
//        webView.loadUrl("http://smartcric.com");
//        webView.loadUrl("https://www.facebook.com/video/embed?video_id=292030124564712");


        webView.loadUrl("file:///android_asset/livetv.html");

//
//        String myLiveStream = "<html>\n" +
//                "<body>\n" +
//                " \n" +
//                "                    <iframe src=\"http://embed.cricket-365.info\" width=\"600\" height=\"370\" id=\"myfr\" scrolling=\"no\" frameborder=\"0\" marginheight=\"0\" marginwidth=\"0\">Your Browser Do not Support Iframe</iframe>\n" +
//                "                    \n" +
//                "</body>\n" +
//                "</html>";
//        String myLiveStream1 = "<html>\n" +
//                "<body>\n" +
//                "<iframe frameborder=\"0\" marginheight=\"0\" marginwidth=\"0\"  height=\"430\" src=\"http://www.crichd.tv/update/willowcricket.php\" name=\"iframe_a\" scrolling=\"no\" width=\"600\">Your Browser Do not Support Iframe</iframe>\n" +
//                "</body>\n" +
//                "</html>";
//        String myLiveStream2 = "<html>\n" +
//                "<body>\n" +
//                "<a href=\"http://p3g.tv/starsports111a\">Live Streaming</a>\n" +
//                "</body>\n" +
//                "</html>";
//
//        String myLiveStream3 = "<html>\n" +
//                "<body>\n" +
//                "   <iframe frameborder=\"0\" marginheight=\"0\" scrolling=\"no\" width=\"600\" height=\"420\" src=\"http://sports247.me/embedcode/star-sports-2/\"></iframe>\n" +
//                "\n" +
//                "</body>\n" +
//                "</html>";
//        String myLiveStream4 = "<html>\n" +
//                "<body>\n" +
//                "   <iframe frameborder=\"0\" marginheight=\"0\" scrolling=\"no\" width=\"600\" height=\"420\" src=\"http://sports247.me/embedcode/star-sports-2/\"></iframe>\n" +
//                "\n" +
//                "</body>\n" +
//                "</html>";
//
//        webView.loadData(myLiveStream2, "text/html", null);


        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);

        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setInitialScale(1);

        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                webView.loadUrl("file:///android_asset/error.html");
                //Toast.makeText(LiveStreamingActivity.this, description, Toast.LENGTH_SHORT).show();
            }
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            // when finish loading page
            public void onPageFinished(WebView view, String url) {
                if (mProgress.isShowing()) {
                    mProgress.dismiss();

//                    webView.loadUrl("<script>location.href='#content_box'</script>");
                }

            }
        });

//        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
//        {
//            @Override
//            public void onRefresh() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        webView.loadUrl("http://smartcric.com");
//                        swipeLayout.setRefreshing(false);
//                    }
//                }, 3000);
//            }
//        });

    }


//    public void onBackPressed() {
//        Intent IntentBack = new Intent(LiveStreamingActivity.this,MainActivity.class);
//        startActivity(IntentBack);
//        finish();
//
//
////        super.onBackPressed();
//    }
@Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(LiveStreamingActivity.this,MainActivity.class);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }


}


