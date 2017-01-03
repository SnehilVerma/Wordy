package com.hackslash.Wordslash.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hackslash.Wordslash.R;
import com.hackslash.Wordslash.models.RareNoun;

/**
 * Created by snehil on 1/1/17.
 */

public class NounHolder extends RecyclerView.ViewHolder {


    public TextView noun;
    public TextView desc;

    public Button details;
    public ImageButton star;



    public NounHolder(View itemView){

        super(itemView);

        //Typeface wordface= Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Directory.ttf");
        //Typeface meanface= Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Directory.ttf");
        //Typeface catface= Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Quicksand_Dash.ttf");


        noun=(TextView)itemView.findViewById(R.id.noun);
        desc=(TextView)itemView.findViewById(R.id.desc);

        details=(Button)itemView.findViewById(R.id.noun_det);
        star=(ImageButton)itemView.findViewById(R.id.star);

    }


    public void bindToPost(RareNoun rareNoun,  View.OnClickListener starListener,
                           View.OnClickListener detClickListener) {

        noun.setText(rareNoun.noun);
        desc.setText(rareNoun.desc);

        details.setOnClickListener(detClickListener);
        star.setOnClickListener(starListener);


    }


    public String getNoun(){
        return noun.getText().toString();
    }

    public String getDesc(){
        return desc.getText().toString();
    }


    public ImageButton getStar(){return star;};


}

