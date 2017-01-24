package com.hackslash.Wordslash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.hackslash.Wordslash.Fragments.BasicList;
import com.hackslash.Wordslash.Fragments.IntermediateList;
import com.hackslash.Wordslash.Fragments.MyFavList;

import hotchemi.android.rate.AppRate;
import hotchemi.android.rate.OnClickButtonListener;
import hotchemi.android.rate.StoreType;

/**
 * Created by snehil on 17/12/16.
 */

public class AllWords extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mPager;
    private FirebaseAuth mAuth;
    private FloatingSearchView mSearchView;
    GoogleApiClient mGoogleApiClient;

    private TextView page_title;







    boolean doubleBackToExitPressedOnce = false;



    // double back pressed exit.
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            //super.onBackPressed();

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);


        mAuth=FirebaseAuth.getInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AllWords.this.setTitle("Wordslash");

        page_title=(TextView)findViewById(R.id.page_title);
        page_title.setText("Lesser Known Verbs");


        //toolbar.setTitleTextColor(Color.WHITE);
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-4707827873780621~7932168994");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);


        //launching app rater on play store.
        AppRate.with(this)
                .setStoreType(StoreType.GOOGLEPLAY) //default is Google, other option is Amazon
                .setInstallDays(5) // default 10, 0 means install day.
                .setLaunchTimes(5) // default 10
                .setRemindInterval(1) // default 1 day.
                .setShowLaterButton(true) // default true.
                .setDebug(false) // default false.
                .setCancelable(false) // default false.
                .setOnClickButtonListener(new OnClickButtonListener() { // callback listener.
                    @Override
                    public void onClickButton(int which) {
                        Log.d(MainActivity.class.getName(), Integer.toString(which));
                    }
                })
                .setTitle(R.string.rate_dialog_title)
                .setTextLater(R.string.rate_dialog_cancel)
                .setTextNever(R.string.rate_dialog_no)
                .setTextRateNow(R.string.rate_dialog_ok)
                .setMessage(R.string.rate_dialog_message)
                .monitor();

        AppRate.showRateDialogIfMeetsConditions(this);

        //AppRater.app_launched(this);



        if (getIntent().getExtras() != null) {

            for (String key : getIntent().getExtras().keySet()) {
                String value = getIntent().getExtras().getString(key);

                if (key.equals("RareNounActivity") && value.equals("True")) {
                    Intent intent = new Intent(this, RareNounActivity.class);
                    intent.putExtra("value", value);
                    startActivity(intent);
                    finish();
                }

            }
        }

        subscribeToPushService();





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        View header=navigationView.getHeaderView(0);
        TextView name=(TextView)header.findViewById(R.id.name);
        TextView mail=(TextView)header.findViewById(R.id.mail);

        name.setText(mAuth.getCurrentUser().getDisplayName());
        mail.setText("Browse our categories!");









        //Toast.makeText(AllWords.this,FirebaseAuth.getInstance().getCurrentUser().getDisplayName(),Toast.LENGTH_SHORT).show();

        mPagerAdapter=new FragmentPagerAdapter(getSupportFragmentManager()){


            private final Fragment[] mFragments=new Fragment[]{
                    new BasicList(),
                    new IntermediateList(),
                    new MyFavList()
            };

            private final String[] mFragmentNames=new String[]{

                    "Basic",
                    "Intermediate",
                    "Favorites"

            };
            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

            @Override
            public int getCount() {
                return mFragments.length;
            }

            @Override
            public CharSequence getPageTitle(int position){
                return mFragmentNames[position];
            }
        };

        mPager = (ViewPager) findViewById(R.id.container);
        mPager.setAdapter(mPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mPager);
    }




    private void subscribeToPushService() {
        FirebaseMessaging.getInstance().subscribeToTopic("news");

        Log.d("Wordslash", "Subscribed");


        String token = FirebaseInstanceId.getInstance().getToken();

        // Log and toast
        Log.d("Wordslash", token);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.all_menu, menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

            return super.onOptionsItemSelected(item);


    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

         if (id == R.id.nav_logout) {

            LoginManager.getInstance().logOut();
            LoginManager.getInstance().logOut();
            mAuth.signOut();
            Intent intent = new Intent(AllWords.this,MainActivity.class);
            intent .setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);


        } else if (id == R.id.nav_fav) {
            //move to favourites tab.
            mPager.setCurrentItem(2);
        }
        else if(id==R.id.nav_less_noun){
            //go to RareNounActivity activity
            Intent i=new Intent(AllWords.this,RareNounActivity.class);
            i .setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);
        }
        else if(id==R.id.nav_less_adj){
            Intent i=new Intent(AllWords.this,RareAdjActivity.class);
            i .setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);

        }
        else if(id==R.id.nav_less_idiom){
            Intent i=new Intent(AllWords.this,RareIdiomActivity.class);
            i .setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

