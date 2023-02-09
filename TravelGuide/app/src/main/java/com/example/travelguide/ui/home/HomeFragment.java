package com.example.travelguide.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.travelguide.ConnectionAsyncTask;
import com.example.travelguide.Destination;
import com.example.travelguide.R;
import com.example.travelguide.databinding.FragmentHomeBinding;

import java.util.List;

public class HomeFragment extends Fragment {
    public LinearLayout linearLayout;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textView = binding.textHome;
        linearLayout = binding.homeLinearrr;
        linearLayout.setBackgroundColor(Color.RED);

        ConnectionAsyncTask connectionAsyncTask = new ConnectionAsyncTask(getActivity());
        connectionAsyncTask.execute("https://run.mocky.io/v3/d1a9c002-6e88-4d1e-9f39-930615876bca");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public interface communicator {
        public void respond(List<Destination> destinations);
    }

    public void fillDestinations(List<Destination> destinations) {
        System.out.println("HERE _________________" + destinations.get(2));
        //LinearLayout linearLayout = (LinearLayout) getView().findViewById(R.id.home_linearrr);
        linearLayout.setBackgroundColor(Color.GREEN);
        linearLayout.removeAllViews();
        for (int i = 0; i < destinations.size(); i++) {
            System.out.println("HERE _________________" +i+ destinations.get(i));
            TextView textView = new TextView(getContext());
            textView.setText(destinations.get(i).toString());
            linearLayout.addView(textView);
        }
    }
}