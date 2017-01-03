package com.hackslash.Wordslash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by snehil on 1/1/17.
 */


public class NounDetailActivity extends BaseActivity {


    //private static final String TAG = "WordDetailActivity";
    //public static final String EXTRA_POST_KEY = "word_key";
    //private String mPostKey;

    //private DatabaseReference mWordReference;

    TextView pro;
    TextView u1;
    TextView u2;
    TextView noun;
    ImageView imageView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noun_detail);


        noun=(TextView)findViewById(R.id.noun_det);
        pro=(TextView)findViewById(R.id.pro);
        u1=(TextView)findViewById(R.id.u1);
        u2=(TextView)findViewById(R.id.u2);
        imageView=(ImageView)findViewById(R.id.image);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://wordslash-16e51.appspot.com");
        StorageReference imagesRef = storageRef.child("Nouns");





        Bundle b=getIntent().getExtras();
        noun.setText(b.getString("word"));
        String pn=b.getString("pro");

        StorageReference spaceRef = imagesRef.child(b.getString("image"));
        String path=spaceRef.getPath();



        showProgressDialog();
        Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(spaceRef).listener(new RequestListener<StorageReference, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, StorageReference model, Target<GlideDrawable> target, boolean isFirstResource) {
                hideProgressDialog();
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, StorageReference model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                hideProgressDialog();
                return false;
            }
        })
                .into(imageView);







        //get file path

        pro.setText("\\"+pn+"\\");
        u1.setText("1)"+"\n"+ b.getString("u1"));
        u2.setText("2)"+"\n"+ b.getString("u2"));


    }
}