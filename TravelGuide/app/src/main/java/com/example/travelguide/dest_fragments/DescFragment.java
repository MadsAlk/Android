package com.example.travelguide.dest_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.travelguide.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DescFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DescFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DescFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DescFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DescFragment newInstance(String param1, String param2) {
        DescFragment fragment = new DescFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    String desc;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_desc, container, false);

        // get arguments
        Bundle bundle = getArguments();
        if (bundle != null) {
            desc = bundle.getString("desc");
            System.out.println("OLAAAAAAAAAAAAAA" + desc);
            TextView descView = (TextView) rootView.findViewById(R.id.text_desc);
            descView.setText(desc);
        }
        // Inflate the layout for this fragment
        return rootView;
    }
}