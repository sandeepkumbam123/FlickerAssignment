package com.skumbam.flickerassignmet;


import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Handler;


/**
 * Created by skumbam on 10/25/18.
 */

public class BindingDataAdapter {


   @BindingAdapter("imageUrl")
    public static void setImageUrl(final ImageView view , ImageResultResponse urlResponseData) {

       if (urlResponseData == null){
           view.setImageDrawable(view.getContext().getResources().getDrawable(R.drawable.ic_launcher_foreground));
       } else {
           final String url = frameImageUrl(urlResponseData);
               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       Bitmap bmp = null;
                       try {
                       URL urlInfo = new URL(url);
                           bmp = BitmapFactory.decodeStream(urlInfo.openConnection().getInputStream());
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                       final Bitmap finalBmp = bmp;
                       view.post(new Runnable() {
                           @Override
                           public void run() {
                               view.setImageBitmap(finalBmp);
                           }
                       });

                   }
               }).start();



       }
    }


    private static String frameImageUrl(ImageResultResponse response) {
       return "http://farm"+response.getFarm()+".static.flickr.com/"+response.getServer()
               +"/"+response.getId()+"_"+response.getSecret()+".jpg";
    }
}
