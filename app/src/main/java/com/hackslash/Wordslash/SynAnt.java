package com.hackslash.Wordslash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import static com.hackslash.Wordslash.R.id.syn1;

/**
 * Created by snehil on 19/12/16.
 */

public class SynAnt extends AppCompatActivity {




    TextView stv1;
    TextView stv2;
    TextView stv3;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.synant);

        stv1=(TextView)findViewById(syn1);
        stv2=(TextView)findViewById(R.id.syn2);


        Bundle b=getIntent().getExtras();



        stv1.setText("1)"+" "+ b.getString("s1"));
        stv2.setText("2)"+" "+ b.getString("s2"));




    }
}
