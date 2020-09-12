package com.luminous.android.downloadingimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
    }

    public void download(View view) {

    }

    public class ImageDownloader extends AsyncTaskLoader<Bitmap> {

        public ImageDownloader(@NonNull Context context) {
            super(context);
        }

        @Nullable
        @Override
        public Bitmap loadInBackground(String... urls) throws MalformedURLException {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
            } catch (Exception e) {

            }

            return null;
        }
    }
}