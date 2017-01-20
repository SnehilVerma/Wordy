package com.hackslash.Wordslash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static com.hackslash.Wordslash.R.id.syn1;

/**
 * Created by snehil on 19/12/16.
 */

public class SynAnt2 extends AppCompatActivity {



    TextView atv1;
    TextView atv2;
    TextView atv3;

    TextView stv1;
    TextView stv2;
    TextView stv3;
    RelativeLayout rlu;
    RelativeLayout rll;
    ImageView ivs;
    ImageView iva;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.synant2);


        rlu=(RelativeLayout)findViewById(R.id.upper);
        rll=(RelativeLayout)findViewById(R.id.lower);
        iva=(ImageView)findViewById(R.id.antbigsmall);
        ivs=(ImageView)findViewById(R.id.syntired);





        atv1=(TextView)findViewById(R.id.ant1);
        atv2=(TextView)findViewById(R.id.ant2);


        stv1=(TextView)findViewById(syn1);
        stv2=(TextView)findViewById(R.id.syn2);


        Bundle b=getIntent().getExtras();

        String stat=b.getString("stat");
        if(stat.equals("ant")){
            rll.setVisibility(View.GONE);
            ivs.setVisibility(View.GONE);
            atv1.setText(b.getString("a1"));
            atv2.setText(b.getString("a2"));

        }
        else if(stat.equals("syn")){

            rlu.setVisibility(View.GONE);
            iva.setVisibility(View.GONE);
            stv1.setText(b.getString("s1"));
            stv2.setText(b.getString("s2"));


        }










    }
}
