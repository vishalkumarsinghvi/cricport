package cricport.com.cricport;

import android.app.Activity;
import android.net.Uri;
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


public class LiveScoreFragment extends Fragment {
    private WebView webView;
    public LiveScoreFragment() {
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
        swipeLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeLayout.setEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Score");
        webView = (WebView) rootView.findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);

      //  webView.loadUrl("http://cricport.16mb.com/score.html");
        if(CheckNetwork.isInternetAvailable(getActivity())) //returns true if internet available
        {
        webView.loadUrl("file:///android_asset/score.html");
//        String myScore = "<html>\n" +
//                "<body>\n" +
//                "<script type=\"text/javascript\"> \n" +
//                " app=\"www.cricwaves.com\"; mo=\"f1_zd\"; nt=\"ind\"; mats =\"\"; tor =\"\"; Width='350px'; Height='600px'; wi =\"w\"; \n" +
//                " co =\"ind\"; ad=\"1\"; \n" +
//                "</script>\n" +
//                "<script type=\"text/javascript\" src=\"http://www.cricwaves.com/cricket/widgets/script/scoreWidgets.js\"></script>                  \n" +
//                "</body>\n" +
//                "</html>";
//        webView.loadData(myScore, "text/html", null);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                webView.loadUrl("file:///android_asset/errorverticle.html");
//                Toast.makeText(getActivity(), description, Toast.LENGTH_SHORT).show();
            }
        });

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        webView.loadUrl("file:///android_asset/score.html");
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
