package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {

    //Public fields to be parsable
    String backdropPath;
    String posterPath;
    String title;
    String overview;
    Double voterAverage;

    String releaseDate;

    Double mvPop;


    //Default constructor for the parser
    public Movie(){}

    //Constructor for object movie

    public Movie(JSONObject jsonObject) throws JSONException {
        backdropPath = jsonObject.getString(("backdrop_path"));
        posterPath = jsonObject.getString("poster_path");

        title = jsonObject.getString("title");
        overview = jsonObject.getString(("overview"));

        voterAverage = jsonObject.getDouble("vote_average");
        releaseDate = jsonObject.getString("release_date");
        mvPop = jsonObject.getDouble("popularity");
    }
    //Creating the list of movies

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for(int i = 0; i < movieJsonArray.length(); i++){
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }return movies;
    }

    //Getter methods
    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    public String getPosterPath() {
        //Formatting exact size
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }
    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public Double getVoterAverage() {
        return voterAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Double getMvPop() {
        return mvPop;
    }
}
