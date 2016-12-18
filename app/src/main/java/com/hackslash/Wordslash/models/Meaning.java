package com.hackslash.Wordslash.models;

/**
 * Created by snehil on 16/12/16.
 */

public class Meaning {


    public String word;
    public String meaning;
    public String category;

    public Meaning() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Meaning(String word,String meaning,String category) {

        this.word=word;
        this.meaning = meaning;
        this.category = category;

    }


    public String getCategory(){
        return category;
    }


}
