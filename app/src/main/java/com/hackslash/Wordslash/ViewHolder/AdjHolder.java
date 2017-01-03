package com.hackslash.Wordslash.ViewHolder;



import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hackslash.Wordslash.R;
import com.hackslash.Wordslash.models.Adjective;

/**
 * Created by snehil on 3/1/17.
 */

public class AdjHolder extends RecyclerView.ViewHolder {

    public TextView adj;
    public TextView meaning;



    public Button antonyms;
    public Button synonyms;
    public ImageButton star;







    public AdjHolder(View itemView){

        super(itemView);

        //Typeface wordface= Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Directory.ttf");
        //Typeface meanface= Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Directory.ttf");
        //Typeface catface= Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Quicksand_Dash.ttf");


        adj=(TextView)itemView.findViewById(R.id.adj);
        meaning=(TextView)itemView.findViewById(R.id.meaning);
        antonyms=(Button)itemView.findViewById(R.id.ant);
        synonyms=(Button)itemView.findViewById(R.id.syn);
        star=(ImageButton)itemView.findViewById(R.id.star);






    }

    public void bindToPost(Adjective adjective, View.OnClickListener antClickListener, View.OnClickListener synClickListener,
                           View.OnClickListener starListener) {

        adj.setText(adjective.adj);
        meaning.setText(adjective.meaning);
        // numStarsView.setText(String.valueOf(complaint.starCount));

        //starView.setOnClickListener(starClickListener);
        antonyms.setOnClickListener(antClickListener);
        synonyms.setOnClickListener(synClickListener);
        star.setOnClickListener(starListener);


    }


    public String getAdj(){
        return adj.getText().toString();
    }

    public String getMeaning(){
        return meaning.getText().toString();
    }


    public ImageButton getStar(){return star;};


}


