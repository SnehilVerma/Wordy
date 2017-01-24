package com.hackslash.Wordslash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by snehil on 18/12/16.
 */

public class WordDetailActivity extends AppCompatActivity {


    //private static final String TAG = "WordDetailActivity";
    //public static final String EXTRA_POST_KEY = "word_key";
    //private String mPostKey;

    //private DatabaseReference mWordReference;

    TextView pro;
    TextView u1;
    TextView u2;
    TextView word;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_detail);

        word=(TextView)findViewById(R.id.word_det);
        pro=(TextView)findViewById(R.id.pro);
        u1=(TextView)findViewById(R.id.u1);
        u2=(TextView)findViewById(R.id.u2);

        Bundle b=getIntent().getExtras();
        word.setText(b.getString("word"));
        String pn=b.getString("pro");
        pro.setText("\\"+pn+"\\");
        u1.setText("1)"+"\n"+ b.getString("u1"));
        u2.setText("2)"+"\n"+ b.getString("u2"));



        //u2.setText("2)"+"\n"+ b.getString("u2"));









    }
}
