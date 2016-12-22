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



    TextView atv1;
    TextView atv2;
    TextView atv3;

    TextView stv1;
    TextView stv2;
    TextView stv3;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.synant);

        atv1=(TextView)findViewById(R.id.ant1);
        atv2=(TextView)findViewById(R.id.ant2);
        atv3=(TextView)findViewById(R.id.ant3);

        stv1=(TextView)findViewById(syn1);
        stv2=(TextView)findViewById(R.id.syn2);
        stv3=(TextView)findViewById(R.id.syn3);

        Bundle b=getIntent().getExtras();
        atv1.setText(b.getString("a1"));
        atv2.setText(b.getString("a2"));
        atv3.setText(b.getString("null"));


        stv1.setText(b.getString("s1"));
        stv2.setText(b.getString("s2"));
        stv3.setText(b.getString("null"));



    }
}
