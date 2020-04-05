package cricport.com.cricport;

import android.app.Activity;
import android.app.ProgressDialog;
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


public class NewsFragment extends Fragment {
    private WebView webView;
    private ProgressDialog mProgress;

    public NewsFragment() {
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
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("News");
        webView = (WebView) rootView.findViewById(R.id.webView1);
        final SwipeRefreshLayout swipeView =(SwipeRefreshLayout)rootView.findViewById(R.id.swipe);
        swipeView.setColorScheme(android.R.color.holo_blue_dark,android.R.color.holo_blue_light, android.R.color.holo_green_light,android.R.color.holo_green_dark);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        if(CheckNetwork.isInternetAvailable(getActivity())) //returns true if internet available
        {
            mProgress = ProgressDialog.show(getActivity(), "Loading", "Please wait for a moment...");
//        mProgress = new ProgressDialog(getActivity(), R.drawable.cricportimg);
//        mProgress.show();

//        webView.loadUrl("http://timesofindia.indiatimes.com/rssfeedsvideo/3812907.cms");

            final String myNews = "<html>\n" +
                    "<body>\n" +
                    "<!-- start feedwind code --><script type=\"text/javascript\">document.write('\\x3Cscript type=\"text/javascript\" src=\"' + ('https:' == document.location.protocol ? 'https://' : 'http://') + 'feed.mikle.com/js/rssmikle.js\">\\x3C/script>');</script><script type=\"text/javascript\">(function() {var params = {rssmikle_url: \"https://news.google.co.in/news?cf=all&hl=en&pz=1&ned=in&topic=s&output=rss\",rssmikle_frame_width: \"350\",rssmikle_frame_height: \"600\",frame_height_by_article: \"0\",rssmikle_target: \"_blank\",rssmikle_font: \"Arial, Helvetica, sans-serif\",rssmikle_font_size: \"12\",rssmikle_border: \"off\",responsive: \"on\",rssmikle_css_url: \"\",text_align: \"left\",text_align2: \"left\",corner: \"off\",scrollbar: \"on\",autoscroll: \"on\",scrolldirection: \"up\",scrollstep: \"5\",mcspeed: \"20\",sort: \"Off\",rssmikle_title: \"off\",rssmikle_title_sentence: \"\",rssmikle_title_link: \"\",rssmikle_title_bgcolor: \"#0066FF\",rssmikle_title_color: \"#FFFFFF\",rssmikle_title_bgimage: \"\",rssmikle_item_bgcolor: \"#FFFFFF\",rssmikle_item_bgimage: \"\",rssmikle_item_title_length: \"55\",rssmikle_item_title_color: \"#E07624\",rssmikle_item_border_bottom: \"on\",rssmikle_item_description: \"on\",item_link: \"off\",rssmikle_item_description_length: \"150\",rssmikle_item_description_color: \"#666666\",rssmikle_item_date: \"gl1\",rssmikle_timezone: \"Etc/GMT\",datetime_format: \"%b %e, %Y %l:%M %p\",item_description_style: \"text+tn\",item_thumbnail: \"full\",item_thumbnail_selection: \"auto\",article_num: \"15\",rssmikle_item_podcast: \"off\",keyword_inc: \"\",keyword_exc: \"\"};feedwind_show_widget_iframe(params);})();</script><div style=\"font-size:10px; text-align:center; \"><a href=\"http://cricport.16mb.com/\" target=\"_blank\" style=\"color:#CCCCCC;\">Cricport Website</a><!--Please display the above link in your web page according to Terms of Service.--></div><!-- end feedwind code --><!--  end  feedwind code -->\n" +
                    "</body>\n" +
                    "</html>";
            webView.loadData(myNews, "text/html", null);

            swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    swipeView.setRefreshing(true);
                    (new Handler()).postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            webView.loadData(myNews, "text/html", null);
                            swipeView.setRefreshing(false);
                        }
                    }, 3000);
                }
            });

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
        }
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
