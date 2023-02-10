package com.example.travelguide.sort;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelguide.Destination;
import com.example.travelguide.DestinationJsonParser;
import com.example.travelguide.R;
import com.example.travelguide.all.AllFragment;
import com.example.travelguide.all.MyOnTouchListener;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortFragment extends Fragment {
    LinearLayout linearLayout;
    TextView textView;
    public static SortFragment newInstance() {
        return new SortFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sort, container, false);
        // Get a reference to the Views

        final ImageView imageView = (ImageView) view.findViewById(R.id.img_anim);
        Button btnAsc = view.findViewById(R.id.btn_asc);
        Button btnDes = view.findViewById(R.id.btn_des);
        linearLayout = view.findViewById(R.id.sort_layout_dest);

        btnAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setVisibility(View.VISIBLE);
                imageView.startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.movex));
                fillDestinations(1);
                imageView.setVisibility(View.INVISIBLE);
            }
        });

        btnDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fillDestinations(0);
                imageView.setVisibility(View.VISIBLE);
                imageView.startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.move_desc));
                imageView.setVisibility(View.INVISIBLE);
            }
        });


        return view;
    }









    public void fillDestinations(int order) {
        linearLayout.removeAllViews();
        List<Destination> destinations = DestinationJsonParser.destinations;
        if(order == 1){
            Collections.sort(destinations, new Comparator<Destination>() {
                @Override
                public int compare(Destination o1, Destination o2) {
                    return o1.getCost() - o2.getCost();
                }
            });
        }else{
            Collections.sort(destinations, new Comparator<Destination>() {
                @Override
                public int compare(Destination o1, Destination o2) {
                    return o2.getCost() - o1.getCost();
                }
            });
        }

        //System.out.println("HERE _________________" + destinations.get(2));

        for (int i = 0; i < destinations.size(); i++) {
            Destination customObject = destinations.get(i);

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
                    Toast.makeText(getContext(), destinations.get(0).getCity(), Toast.LENGTH_SHORT).show();
                }
            });

            TextView textView2 = new TextView(getContext());
            textView2.setText(customObject.getCountry());
            textView2.setTextSize(24);
            textView2.setPadding(16, 16, 16, 16);
            textView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), destinations.get(1).getCity(), Toast.LENGTH_SHORT).show();
                }
            });

            innerLinearLayout.addView(textView1);
            innerLinearLayout.addView(textView2);


            if (i % 2 == 0) {
                innerLinearLayout.setBackgroundColor(Color.LTGRAY);
            } else {
                innerLinearLayout.setBackgroundColor(Color.WHITE);
            }

            linearLayout.addView(innerLinearLayout);
            linearLayout.setOnTouchListener(new MyOnTouchListener());
        }
    }














        @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}