package cricport.com.cricport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements DrawerFragment.FragmentDrawerListener {
    private DrawerFragment drawerFragment;
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (DrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.activity_main_fragment_navigation_drawer);
        drawerFragment.setUp(R.id.activity_main_fragment_navigation_drawer, mDrawerLayout, mToolbar);
        drawerFragment.setDrawerListener(this);
        displayView(1);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent IntentMainWeb = new Intent(MainActivity.this, MainWebsiteActivity.class);
            MainActivity.this.startActivity(IntentMainWeb);
            MainActivity.this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }


    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
//            case 0:
//                fragment = new EditMyProfileFragment();
//                title = getString(R.string.nav_item_edit);
//                break;
            case 1:
                fragment = new NewsFragment();
                title = getString(R.string.nav_item_news);
                break;
            case 2:
                fragment = new VideosFragment();
                title = getString(R.string.nav_item_video);
                break;
            case 3:
                fragment = new PhotosFragment();
                title = getString(R.string.title_photos);

                break;
            case 4:
                fragment = new LiveScoreFragment();
                title = getString(R.string.title_score);
                break;
            case 5:
                Intent IntentBack = new Intent(MainActivity.this, LiveStreamingActivity.class);
                MainActivity.this.startActivity(IntentBack);
                MainActivity.this.finish();
//                fragment = new LiveStreamingFragment();
//                title = getString(R.string.title_live_stream);
                break;
            case 6:
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                fragment = new IccRatingFragment();
                title = getString(R.string.title_icc_rating);
                break;

            case 7:
                fragment = new FullScoreBoardFragment();
                title = getString(R.string.title_full_scoreboard);

                break;
            case 8:
                fragment = new EditMyProfileFragment();
                title = getString(R.string.title_edit);

                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.commit();
            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

}

