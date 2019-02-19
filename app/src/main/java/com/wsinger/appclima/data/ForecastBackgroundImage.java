package com.wsinger.appclima.data;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import android.os.AsyncTask;

import android.support.constraint.ConstraintLayout;


import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.SearchParameters;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class ForecastBackgroundImage extends AsyncTask<Void,Void,Drawable> {
    //ImageView imageView;
    ConstraintLayout clayout;
    Context context;
    public ForecastBackgroundImage(ConstraintLayout lay,Context context){ // (ImageView im){
        //imageView=im;
        this.clayout = lay;
        this.context = context;
    }

    @Override
    protected Drawable doInBackground(Void... voids) {

        PhotoList photoList = null;
        String flickrApikey = "2739b9ccb1780b5d54227501a98df032";
        String flickrSecret = "91da2274ed2e09c6";

        REST rest = new REST();
        Flickr client = new Flickr(flickrApikey,flickrSecret,rest);

        SearchParameters searchParameters = new SearchParameters();
        //searchParameters.setTags (SearchParameters.INTERESTINGNESS_DESC );
        String[] tags = new String[]{"places"};
        searchParameters.setTags(tags);


        try {
            photoList = client.getPhotosInterface().search(searchParameters,20,1);
            Photo p = (Photo) photoList.get((int) (Math.random()*20)+1);
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(p.getMediumUrl()).getContent());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(),bitmap);
            return bitmapDrawable;
        } catch (FlickrException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

        //return p;
        return null;
    }

    @Override
    protected void onPostExecute(Drawable bitmap){ //(Photo photo) {
        //String urlImage = photo.getMediumUrl();

        //Picasso.get().load(urlImage).into();
        if(bitmap != null) {
            this.clayout.setBackground(bitmap);
        }
    }
}
