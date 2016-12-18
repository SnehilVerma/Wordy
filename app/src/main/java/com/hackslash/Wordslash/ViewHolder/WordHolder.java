package com.hackslash.Wordslash.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hackslash.Wordslash.R;
import com.hackslash.Wordslash.models.Meaning;

/**
 * Created by snehil on 17/12/16.
 */

public class WordHolder extends RecyclerView.ViewHolder {

    public TextView word;
    public TextView meaning;
    public TextView category;

    public Button antonyms;
    public Button synonyms;





    public WordHolder(View itemView){

        super(itemView);

        //Typeface wordface= Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Directory.ttf");
        //Typeface meanface= Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Directory.ttf");
        //Typeface catface= Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Quicksand_Dash.ttf");


        word=(TextView)itemView.findViewById(R.id.word);
        meaning=(TextView)itemView.findViewById(R.id.meaning);
        category=(TextView)itemView.findViewById(R.id.cat);
        antonyms=(Button)itemView.findViewById(R.id.ant);
        synonyms=(Button)itemView.findViewById(R.id.syn);





    }

    public void bindToPost(Meaning meanings,View.OnClickListener antClickListener) {

        word.setText(meanings.word);
        meaning.setText(meanings.meaning);
        // numStarsView.setText(String.valueOf(complaint.starCount));
        category.setText(meanings.category);

        //starView.setOnClickListener(starClickListener);
        antonyms.setOnClickListener(antClickListener);
    }

    public String getWord(){
        return word.getText().toString();
    }



}
