package com.example.travelguide.dest_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.travelguide.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    double lng;
    double lat;
    private MapView mapv;
    private MapController mapController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        // get arguments
        Bundle bundle = getArguments();
        if (bundle != null) {
            lng = bundle.getDouble("lng");
            lat = bundle.getDouble("lat");

            MapView map = (MapView) rootView.findViewById(R.id.map);
            map.setTileSource(TileSourceFactory.MAPNIK);
            map.setBuiltInZoomControls(true);
            map.setMultiTouchControls(true);
            map.getController().setCenter(new GeoPoint(lat, lng));
            map.getController().setZoom(16);

//            mapv = rootView.findViewById(R.id.map);
//            mapv.setTileSource(TileSourceFactory.MAPNIK);
//            mapv.setBuiltInZoomControls(true);
//            mapv.setMultiTouchControls(true);
//
//            mapController = (MapController) mapv.getController();
//            mapController.setZoom(16);
//            GeoPoint center = new GeoPoint(lat, lng);
//            mapController.setCenter(center);

//            MapView mapView = rootView.findViewById(R.id.mapView);
//            mapView.onCreate(savedInstanceState);
//            mapView.getMapAsync(new OnMapReadyCallback() {
//                @Override
//                public void onMapReady(GoogleMap googleMap) {
//                    LatLng latLng = new LatLng(lat, lng);
//                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
//                    googleMap.addMarker(new MarkerOptions().position(latLng));
//                }
//            });


            //mapView.getController().setCenter(new GeoPoint(latitude, longitude));



//            mapView.getMapAsync(new OnMapReadyCallback() {
//                @Override
//                public void onMapReady(GoogleMap googleMap) {
////                    double latitude = 37.4219999;
////                    double longitude = -122.0840575;
//                    LatLng target = new LatLng(lat, lng);
//                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(target);
//                    googleMap.animateCamera(cameraUpdate);
//                }
//            });
        }




        // Inflate the layout for this fragment
        return rootView;
    }
}