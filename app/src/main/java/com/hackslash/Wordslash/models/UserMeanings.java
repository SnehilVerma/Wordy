package com.hackslash.Wordslash.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by snehil on 16/12/16.
 */

public class UserMeanings {


    public String u_id;
    public String u_name;
    public String word;
    public String meaning;
    public String cat_id;
    public Map<String, Boolean> star = new HashMap<>();


    public UserMeanings(){}


    public UserMeanings(String u_id,String u_name,String word,String meaning,String category,boolean t_value) {

        this.u_id=u_id;
        this.u_name=u_name;
        this.word=word;
        this.meaning = meaning;
        this.cat_id = category;
        this.star.put(u_id,t_value);

        //this.dept=dept;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", u_id);
        result.put("uname", u_name);
        result.put("word", word);
        result.put("meaning", meaning);
        result.put("category", cat_id);
        result.put("star",star);
        //       result.put("starCount", starCount);
        //      result.put("stars",stars);
        return result;
    }


}
