package com.hackslash.Wordslash.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by snehil on 4/1/17.
 */

public class UserIdiom {

    public String u_id;
    public String u_name;
    public String meaning;
    public String idiom;
    public String category;

    public Map<String, Boolean> star = new HashMap<>();


    public UserIdiom(){}


    public UserIdiom(String u_id,String u_name,String idiom,String meaning,String category,boolean t_value) {

        this.u_id=u_id;
        this.u_name=u_name;
        this.idiom=idiom;
        this.meaning = meaning;
        this.category=category;
        this.star.put(u_id,t_value);


        //this.dept=dept;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", u_id);
        result.put("uname", u_name);
        result.put("idiom", idiom);
        result.put("meaning", meaning);
        result.put("category",category);
        result.put("star",star);
        //       result.put("starCount", starCount);
        //      result.put("stars",stars);
        return result;
    }


}
