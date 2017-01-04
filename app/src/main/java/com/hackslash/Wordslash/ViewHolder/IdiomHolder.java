package com.hackslash.Wordslash.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hackslash.Wordslash.R;
import com.hackslash.Wordslash.models.Idiom;

/**
 * Created by snehil on 4/1/17.
 */

public class IdiomHolder extends RecyclerView.ViewHolder{

    public TextView idiom;
    public TextView meaning;
    public TextView category;
    public ImageButton star;



    public IdiomHolder(View itemView){

        super(itemView);

        //Typeface wordface= Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Directory.ttf");
        //Typeface meanface= Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Directory.ttf");
        //Typeface catface= Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Quicksand_Dash.ttf");


        idiom=(TextView)itemView.findViewById(R.id.idiom);
        meaning=(TextView)itemView.findViewById(R.id.meaning);
        category=(TextView)itemView.findViewById(R.id.cat);

        star=(ImageButton)itemView.findViewById(R.id.star);

    }


    public void bindToPost(Idiom rareIdiom, View.OnClickListener starListener
                       ) {

        idiom.setText(rareIdiom.idiom);
        meaning.setText(rareIdiom.meaning);
        category.setText(rareIdiom.category);
        star.setOnClickListener(starListener);


    }


    public String getIdiom(){
        return idiom.getText().toString();
    }

    public String getMeaning(){
        return meaning.getText().toString();
    }

    public String getCategory(){ return category.getText().toString();}

    public ImageButton getStar(){return star;};


}
