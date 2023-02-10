package com.example.travelguide.fav;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.travelguide.DataBaseHelper;
import com.example.travelguide.Destination;
import com.example.travelguide.DestinationActivity;
import com.example.travelguide.DestinationJsonParser;
import com.example.travelguide.R;
import com.example.travelguide.User;
import com.example.travelguide.all.MyOnTouchListener;

import java.util.ArrayList;
import java.util.List;

public class FavFragment extends Fragment {
    LinearLayout linearLayout;
    TextView textView;


    public static FavFragment newInstance() {
        return new FavFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fav, container, false);
        textView = rootView.findViewById(R.id.fav_text);
        linearLayout = rootView.findViewById(R.id.fav_layout);

        getFavs();



        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void getFavs() {
        String pdest = "";

        if(User.flag == 0){ User.flag = 1;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext(), "TRAVEL", null, 1);
        Cursor userCursor = dataBaseHelper.getUser(User.currentEmail);
        if (userCursor != null && userCursor.moveToFirst()) {
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
                if (!User.favorites.contains(destination))
                    User.favorites.add(destination);
            }
        }
    }


        linearLayout.removeAllViews();
        for (int i = 0; i < User.favorites.size(); i++) {
            Destination customObject = User.favorites.get(i);

            LinearLayout innerLinearLayout = new LinearLayout(getContext());
            innerLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            innerLinearLayout.setGravity(Gravity.CENTER_VERTICAL);

            TextView textView1 = new TextView(getContext());
            textView1.setText(customObject.getCity());
            textView1.setTextSize(24);
            textView1.setPadding(16, 16, 16, 16);
            textView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getContext(), destinations.get(0).getCity(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), DestinationActivity.class);
                    intent.putExtra("dest", customObject.getCity());
                    startActivity(intent);
                }
            });

            TextView textView2 = new TextView(getContext());
            textView2.setText(customObject.getCountry());
            textView2.setTextSize(24);
            textView2.setPadding(16, 16, 16, 16);
            textView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toast.makeText(getContext(), destinations.get(1).getCity(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), DestinationActivity.class);
                    intent.putExtra("dest", customObject.getCity());
                    startActivity(intent);
                }
            });

            Button btn_del = new Button(getContext());
            btn_del.setText("Remove");
            btn_del.setTextColor(Color.BLUE);
            btn_del.setOnClickListener(new View.OnClickListener() {
                int index=-1;
                @Override
                public void onClick(View v) {
                    String city = textView1.getText().toString();
                    for (Destination destination : User.favorites) {
                        if (destination.getCity().equals(city)) {
                            index = User.favorites.indexOf(destination);
                        }
                    }
                    System.out.println("REMOVED " + User.favorites.get(index));
                    User.favorites.remove(index);
                    getFavs();

                }
            });

            innerLinearLayout.addView(textView1);
            innerLinearLayout.addView(textView2);
            innerLinearLayout.addView(btn_del);


            if (i % 2 == 0) {
                innerLinearLayout.setBackgroundColor(Color.LTGRAY);
            } else {
                innerLinearLayout.setBackgroundColor(Color.WHITE);
            }

            linearLayout.addView(innerLinearLayout);
            linearLayout.setOnTouchListener(new MyOnTouchListener());
        }
    }
}


