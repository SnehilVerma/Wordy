package com.hackslash.Wordslash;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.hackslash.Wordslash.Fragments.BasicList;
import com.hackslash.Wordslash.Fragments.IntermediateList;

/**
 * Created by snehil on 17/12/16.
 */

public class AllWords extends AppCompatActivity {

    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mPager;
    private FirebaseAuth mAuth;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mAuth.signOut();
        finish();

    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat_layout);

        mAuth=FirebaseAuth.getInstance();




        setTitle(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());


        //Toast.makeText(AllWords.this,FirebaseAuth.getInstance().getCurrentUser().getDisplayName(),Toast.LENGTH_SHORT).show();

        mPagerAdapter=new FragmentPagerAdapter(getSupportFragmentManager()){


            private final Fragment[] mFragments=new Fragment[]{
                    new BasicList(),
                    new IntermediateList(),
            };

            private final String[] mFragmentNames=new String[]{

                    "Basic",
                    "Intermediate"
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

}

