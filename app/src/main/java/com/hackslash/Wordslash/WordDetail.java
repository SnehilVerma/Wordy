package com.hackslash.Wordslash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by snehil on 18/12/16.
 */

public class WordDetail extends AppCompatActivity {


    private static final String TAG = "WordDetail";
    public static final String EXTRA_POST_KEY = "word_key";
    private String mPostKey;

    //private DatabaseReference mWordReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_detail);

        Bundle b=getIntent().getExtras();






    }
}
