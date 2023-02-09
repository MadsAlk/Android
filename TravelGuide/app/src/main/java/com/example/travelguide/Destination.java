package com.example.travelguide;

public class Destination {
    private String city;
    private String country;
    private String continent;
    private Double longitude;
    private Double latitude;
    private int cost;
    private String img;
    private String description;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Destination{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", continent='" + continent + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", cost=" + cost +
                ", img='" + img + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}