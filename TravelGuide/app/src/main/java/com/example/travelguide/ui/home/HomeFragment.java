package com.example.travelguide.ui.home;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.travelguide.ConnectionAsyncTask;
import com.example.travelguide.DataBaseHelper;
import com.example.travelguide.Destination;
import com.example.travelguide.DestinationJsonParser;
import com.example.travelguide.R;
import com.example.travelguide.SignInActivity;
import com.example.travelguide.User;
import com.example.travelguide.databinding.FragmentHomeBinding;
import com.example.travelguide.dest_fragments.ImgFragment;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//DataBaseHelper dataBaseHelper =new DataBaseHelper(SignInActivity.this,"TRAVEL", null,1);

public class HomeFragment extends Fragment {
    public LinearLayout linearLayout;
    private FragmentHomeBinding binding;
    public String pdest;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        DataBaseHelper dataBaseHelper =new DataBaseHelper(getContext(),"TRAVEL", null,1);
        Cursor userCursor = dataBaseHelper.getUser(User.currentEmail);
        if(userCursor != null && userCursor.moveToFirst() ) {
            pdest = userCursor.getString(4);
        }

        //SELECT RANDOM DESTINATION FROM PREFERED CONTINENT________________________________________________
        System.out.println(pdest);
        List<Destination> destinations = DestinationJsonParser.destinations;
        String targetContinent = pdest;

// Create a list of destinations that have the target continent
        ArrayList<Destination> filteredDestinations = new ArrayList<>();
        for (Destination destination : destinations) {
            if (destination.getContinent().equals(targetContinent)) {
                filteredDestinations.add(destination);
            }
        }

// If there are no destinations with the target continent, return null
        if (filteredDestinations.isEmpty()) {
            return null;
        }

// Select a random destination from the filtered destinations
        Random random = new Random();
        int randomIndex = random.nextInt(filteredDestinations.size());
        Destination randomDestination = filteredDestinations.get(randomIndex);
        //______________________________________________________________________________________
        System.out.println(randomDestination.getCity());

        TextView city = (TextView) root.findViewById(R.id.home_city);
        TextView counrty = (TextView) root.findViewById(R.id.home_country);
        TextView continent = (TextView) root.findViewById(R.id.home_continent);
        TextView cost = (TextView) root.findViewById(R.id.home_cost);
        TextView coord = (TextView) root.findViewById(R.id.home_coords);
        TextView descr = (TextView) root.findViewById(R.id.home_desc);

        String coststr = "Cost " + String.valueOf(randomDestination.getCost());
        String coordstr = "Longitude: " + String.valueOf(randomDestination.getLongitude()) + "     \nLatitude: " + String.valueOf(randomDestination.getLatitude());


        String url = randomDestination.getImg();
        ImageView imageView = root.findViewById(R.id.home_img);
        new HomeFragment.DownloadImageTask(imageView).execute(url);


        city.setText(randomDestination.getCity());
        counrty.setText(randomDestination.getCountry());
        continent.setText(randomDestination.getContinent());
        cost.setText(coststr);
        coord.setText(coordstr);
        descr.setText(randomDestination.getDescription());




        return root;
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




}