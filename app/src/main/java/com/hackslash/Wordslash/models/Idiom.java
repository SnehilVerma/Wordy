package com.hackslash.Wordslash.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by snehil on 4/1/17.
 */

public class Idiom {


    public String idiom;
    public String meaning;
    public String category;
    public Map<String, Boolean> star = new HashMap<>();

    public Idiom() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Idiom(String idiom,String meaning,String category) {

        this.idiom=idiom;
        this.meaning = meaning;
        this.category=category;


    }

}
