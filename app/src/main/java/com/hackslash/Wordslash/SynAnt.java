package com.hackslash.Wordslash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by snehil on 19/12/16.
 */

public class SynAnt extends AppCompatActivity {



    TextView atv1;
    TextView atv2;
    TextView atv3;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.synant);

        atv1=(TextView)findViewById(R.id.ant1);
        atv2=(TextView)findViewById(R.id.ant2);
        atv3=(TextView)findViewById(R.id.ant3);

        Bundle b=getIntent().getExtras();
        atv1.setText(b.getString("a1"));
        atv2.setText(b.getString("a2"));
        atv3.setText(b.getString("null"));





    }
}
