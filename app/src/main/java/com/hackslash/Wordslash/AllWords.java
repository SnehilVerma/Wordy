package com.hackslash.Wordslash;

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
import android.view.MenuItem;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.google.firebase.auth.FirebaseAuth;
import com.hackslash.Wordslash.Fragments.BasicList;
import com.hackslash.Wordslash.Fragments.IntermediateList;
import com.hackslash.Wordslash.Fragments.MyFavList;

/**
 * Created by snehil on 17/12/16.
 */

public class AllWords extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mPager;
    private FirebaseAuth mAuth;
    private FloatingSearchView mSearchView;



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mAuth.signOut();
        finish();

    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        mAuth=FirebaseAuth.getInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AllWords.this.setTitle(mAuth.getCurrentUser().getDisplayName());
        //toolbar.setTitleTextColor(Color.WHITE);


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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Toast.makeText(AllWords.this,"success",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

