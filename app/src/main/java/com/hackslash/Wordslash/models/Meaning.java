package com.hackslash.Wordslash.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by snehil on 16/12/16.
 */

public class Meaning {


    public String word;
    public String meaning;
    public String category;
    public Map<String, Boolean> star = new HashMap<>();

    public Meaning() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Meaning(String word,String meaning,String category) {

        this.word=word;
        this.meaning = meaning;
        this.category = category;



    }


}
