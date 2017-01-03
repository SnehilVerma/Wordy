package com.hackslash.Wordslash;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.facebook.login.LoginManager;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.hackslash.Wordslash.Fragments.AdjFavList;
import com.hackslash.Wordslash.Fragments.AdjectiveList;

/**
 * Created by snehil on 3/1/17.
 */

public class RareAdjActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {


    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mPager;
    private FirebaseAuth mAuth;
    private FloatingSearchView mSearchView;
    GoogleApiClient mGoogleApiClient;

    boolean doubleBackToExitPressedOnce = false;

    private TextView page_title;



    // double back pressed exit.
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rareadj);


        mAuth=FirebaseAuth.getInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RareAdjActivity.this.setTitle(mAuth.getCurrentUser().getDisplayName());
        //toolbar.setTitleTextColor(Color.WHITE);


        page_title=(TextView)findViewById(R.id.page_title);
        page_title.setText("Lesser Known Adjectives");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




        /*
        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {

                //get suggestions based on newQuery

                //pass them on to the search view
                mSearchView.swapSuggestions(newSuggestions);
            }
        });
        */

        //Toast.makeText(AllWords.this,FirebaseAuth.getInstance().getCurrentUser().getDisplayName(),Toast.LENGTH_SHORT).show();

        mPagerAdapter=new FragmentPagerAdapter(getSupportFragmentManager()){


            private final Fragment[] mFragments=new Fragment[]{
                    new AdjectiveList(),
                    new AdjFavList()
            };

            private final String[] mFragmentNames=new String[]{
                    "Adjectives",
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.all_menu, menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_logout){

            LoginManager.getInstance().logOut();
            mAuth.signOut();
            finish();
            return true;


        }
        else {
            return super.onOptionsItemSelected(item);
        }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_help) {
            Toast.makeText(RareAdjActivity.this,"success",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_logout) {

        } else if (id == R.id.nav_fav) {
            //move to favourites tab.
            mPager.setCurrentItem(1);



        }
        else if(id== R.id.nav_home){
            finish();
        }
        else if(id==R.id.nav_less_noun){
            Intent i=new Intent(RareAdjActivity.this, RareNounActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
