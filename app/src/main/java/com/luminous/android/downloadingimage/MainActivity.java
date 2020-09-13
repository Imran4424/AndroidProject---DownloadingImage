package com.luminous.android.downloadingimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
    }

    public void download(View view) {
        Log.d("tapped", "I am here");
        try {
            ImageDownloader task = new ImageDownloader(this);
            Bitmap myImage;

            task.urls.add(getString(R.string.imageUrl));
            myImage = task.loadInBackground();
            imageView.setImageBitmap(myImage);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public class ImageDownloader extends AsyncTaskLoader<Bitmap> {

        List<String> urls = new ArrayList<String>();

        public ImageDownloader(@NonNull Context context) {
            super(context);
        }

        @Nullable
        @Override
        public Bitmap loadInBackground() {
            try {
                URL url = new URL(urls.get(0));
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                // happening error in following line
                connection.connect();

                InputStream inputStream = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);

                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}