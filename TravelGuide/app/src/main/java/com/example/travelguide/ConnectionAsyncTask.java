package com.example.travelguide;

import android.app.Activity;
import android.os.AsyncTask;

import androidx.fragment.app.FragmentManager;

import com.example.travelguide.all.AllFragment;

import java.util.List;

public class ConnectionAsyncTask extends AsyncTask<String, String, String> {
    Activity activity;

    public ConnectionAsyncTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        //((MainActivity) activity).setButtonText("connecting");
        super.onPreExecute();
        //((MainActivity) activity).setProgress(true);
    }

    @Override
    protected String doInBackground(String... params) {
        String data = HttpManager.getData(params[0]);

        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //((MainActivity) activity).setProgress(false);
        //((MainActivity) activity).setButtonText("connected");
        List<Destination> destinations = DestinationJsonParser.getObjectFromJason(s);
        System.out.println("HERE after parser _________________" + destinations.get(1));
//        ((MainActivity) activity).respond(destinations);
//
//        // Get a reference to the FragmentManager
//        FragmentManager fragmentManager = ((MainActivity) activity).getSupportFragmentManager();
//
//// Find the Fragment using its tag or ID
//        AllFragment fragment = (AllFragment) fragmentManager.findFragmentByTag("allFragment");
//
//// Call a function within the Fragment
//        fragment.fillDestinations();


    }
}