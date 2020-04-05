package cricport.com.cricport;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class PhotosFragment extends Fragment {
    private WebView webView;
    private ProgressDialog mProgress;
    public PhotosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.webview, container, false);
        final SwipeRefreshLayout swipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe);
        swipeLayout.setColorSchemeColors(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeLayout.setEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Photo");
        webView = (WebView) rootView.findViewById(R.id.webView1);

//        webView.loadUrl("http://timesofindia.indiatimes.com/rssfeedsvideo/3812907.cms");
        if(CheckNetwork.isInternetAvailable(getActivity())) //returns true if internet available
        {
            mProgress = ProgressDialog.show(getActivity(), "Loading", "Please Wait for a moment...");
        final String myPhotos = "<html>\n" +
                "<body>\n" +
                " \n" +
                "                    <iframe  width=\"350\"  height=\"500\"\n" +
                "\n" +
                "                                   src=\"http://www.espncricinfo.com/ci/content/image/929695.html?object=207428;dir=next\">\n" +
                "                                  &amp;amp;lt;p&amp;amp;gt;Your browser does not\n" +
                "                                  support iframes.&amp;amp;lt;/p&amp;amp;gt; </iframe>\n" +
                "                    \n" +
                "</body>\n" +
                "</html>";
            webView.scrollTo(0,1900);
        webView.loadData(myPhotos, "text/html", null);

            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setDisplayZoomControls(false);
            webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getActivity(), description, Toast.LENGTH_SHORT).show();
            }
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            // when finish loading page
            public void onPageFinished(WebView view, String url) {
                if (mProgress.isShowing()) {
                    mProgress.dismiss();
                }
            }
        });
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        webView.loadData(myPhotos, "text/html", null);
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

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
