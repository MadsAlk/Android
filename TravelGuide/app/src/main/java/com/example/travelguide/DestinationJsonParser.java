package com.example.travelguide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DestinationJsonParser {
    public static List<Destination> destinations = new ArrayList<>();
    public static List<Destination> getObjectFromJason(String jason) {
        try {
            JSONArray jsonArray = new JSONArray(jason);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject = (JSONObject) jsonArray.get(i);
                Destination destination = new Destination();

                destination.setCity(jsonObject.getString("city"));
                destination.setCountry(jsonObject.getString("country"));
                destination.setContinent(jsonObject.getString("continent"));
                destination.setLongitude(jsonObject.getDouble("longitude"));
                destination.setLatitude(jsonObject.getDouble("latitude"));
                destination.setCost(jsonObject.getInt("cost"));
                destination.setImg(jsonObject.getString("img"));
                destination.setDescription(jsonObject.getString("description"));
                destinations.add(destination);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return destinations;
    }
}