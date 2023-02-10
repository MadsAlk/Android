package com.example.travelguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.travelguide.dest_fragments.DescFragment;
import com.example.travelguide.dest_fragments.ImgFragment;
import com.example.travelguide.dest_fragments.MapFragment;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DestinationActivity extends AppCompatActivity {
    String dest;
    Destination activityDest;
    final DescFragment descFragment = new DescFragment();
    final ImgFragment imgFragment = new ImgFragment();
    final MapFragment mapFragment = new MapFragment();
    final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
        dest = getIntent().getStringExtra("dest");
        System.out.println("_______________________"+dest);
        List<Destination> destinations = DestinationJsonParser.destinations;

        activityDest = null;

        for (Destination destination : destinations) {
            System.out.println("_____________+__________"+destination.getCity());
            if (destination.getCity().equals(dest)) {
                activityDest = destination;
                break;
            }
        }

        TextView cityName = (TextView) findViewById(R.id.dest_city);
        cityName.setText(activityDest.getCity());

        Button btn_desc = (Button) findViewById(R.id.btn_desc);
        Button btn_img = (Button) findViewById(R.id.btn_img);
        Button btn_map = (Button) findViewById(R.id.btn_map);


        btn_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("desc", activityDest.getDescription());

                descFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.dest_fragmentContainer, descFragment, null);
                fragmentTransaction.commit();

            }
        });

        btn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("img", activityDest.getImg());

                imgFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.dest_fragmentContainer, imgFragment, null);
                fragmentTransaction.commit();


            }
        });

        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putDouble("lng", activityDest.getLongitude());
                bundle.putDouble("lat", activityDest.getLatitude());

                mapFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.dest_fragmentContainer, mapFragment, null);
                fragmentTransaction.commit();
            }
        });

    }

}