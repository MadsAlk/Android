package com.example.travelguide.all;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.travelguide.ConnectionAsyncTask;
import com.example.travelguide.Destination;
import com.example.travelguide.DestinationActivity;
import com.example.travelguide.DestinationJsonParser;
import com.example.travelguide.MainActivity;
import com.example.travelguide.R;

import java.util.List;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class AllFragment extends Fragment {

    LinearLayout linearLayout;
    TextView textView;
    public static AllFragment newInstance() {
        return new AllFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_all, container, false);
        // Get a reference to the Views
        textView = view.findViewById(R.id.all_text);
        Button btn = view.findViewById(R.id.all_btn);
        linearLayout = view.findViewById(R.id.all_layout);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fillDestinations();
            }
        });
        return view;
    }


    public void fillDestinations() {
        linearLayout.removeAllViews();
        List<Destination> destinations = DestinationJsonParser.destinations;
        //System.out.println("HERE _________________" + destinations.get(2));

//        for (int i = 0; i < destinations.size(); i++) {
//            Destination object = destinations.get(i);
//            LinearLayout subLinearLayout = new LinearLayout(getContext());
//            subLinearLayout.setOrientation(LinearLayout.VERTICAL);
//            if (i % 2 == 0) {
//                subLinearLayout.setBackgroundColor(Color.parseColor("#E0E0E0"));
//            } else {
//                subLinearLayout.setBackgroundColor(Color.WHITE);
//            }
//
//            TextView tvVariable1 = new TextView(getContext());
//            tvVariable1.setText("Variable 1: " + object.getCity());
//            tvVariable1.setTextSize(20);
//            tvVariable1.setTextColor(Color.parseColor("#008CBA"));
//            tvVariable1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(getContext(), destinations.get(1).getCity(), Toast.LENGTH_SHORT).show();
//                }
//            });
//            subLinearLayout.addView(tvVariable1);
//
//            TextView tvVariable2 = new TextView(getContext());
//            tvVariable2.setText("Variable 2: " + object.getCountry());
//            tvVariable2.setTextSize(20);
//            tvVariable2.setTextColor(Color.parseColor("#6A5ACD"));
//            tvVariable2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(getContext(), destinations.get(2).getCity(), Toast.LENGTH_SHORT).show();
//                }
//            });
//            subLinearLayout.addView(tvVariable2);
//
//            TextView tvSeparator = new TextView(getContext());
//            tvSeparator.setText("------------------------------");
//            tvSeparator.setTextSize(20);
//            tvSeparator.setTextColor(Color.GRAY);
//            subLinearLayout.addView(tvSeparator);
//
//            linearLayout.addView(subLinearLayout);
//        }



        //____________________________________________

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
                    Toast.makeText(getContext(), destinations.get(1).getCity(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), DestinationActivity.class);
                    intent.putExtra("dest", customObject.getCity());
                    startActivity(intent);
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

        //_____________________________________________-



//        for (Destination object : destinations) {
//            TextView tvVariable1 = new TextView(getContext());
//            tvVariable1.setText("Variable 1: " + object.getCity());
//            tvVariable1.setTextSize(20);
//            tvVariable1.setTextColor(Color.parseColor("#008CBA"));
//            linearLayout.addView(tvVariable1);
//
//            TextView tvVariable2 = new TextView(getContext());
//            tvVariable2.setText("Variable 2: " + object.getCountry());
//            tvVariable2.setTextSize(20);
//            tvVariable2.setTextColor(Color.parseColor("#6A5ACD"));
//            linearLayout.addView(tvVariable2);
//
//            TextView tvSeparator = new TextView(getContext());
//            tvSeparator.setText("------------------------------");
//            tvSeparator.setTextSize(20);
//            tvSeparator.setTextColor(Color.GRAY);
//            linearLayout.addView(tvSeparator);
//        }



//        textView.setText(destinations.get(2).toString());
//        linearLayout.setBackgroundColor(Color.GREEN);
//        linearLayout.removeAllViews();
//        for (int i = 0; i < destinations.size(); i++) {
//            System.out.println("HERE _________________" +i+ destinations.get(i));
//            TextView textView = new TextView(getContext());
//            textView.setText(destinations.get(i).toString());
//            linearLayout.addView(textView);
//        }
    }


















    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}