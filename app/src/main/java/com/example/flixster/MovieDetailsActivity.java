package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.flixster.databinding.ActivityMovieDetailsBinding;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieDetailsActivity extends AppCompatActivity {

    Movie movie;

    //Not needed with binding
    //TextView tvTitle;
    //TextView tvOverview;
    //RatingBar rbVoteAverage;
    //TextView rDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Following line deleted to reduce boilerplate
        //setContentView(R.layout.activity_movie_details);
        ActivityMovieDetailsBinding binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //view objects
        //Not needed with binding
        //tvTitle = binding.tvTitle;
        //tvOverview = binding.tvOverview;
        //rbVoteAverage = binding.rbVoteAverage;
        //rDate = binding.rDate;

        //Unwrap movie passed from intent
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MoviesDetailsActivity", String.format("Showing details for '%s:'", movie.getTitle()));

        //Set title, overview, vote average, release date
        //added binding. to reduce boilerplate
        binding.tvTitle.setText(movie.getTitle());
        binding.tvOverview.setText(movie.getOverview());
        float voteAverage = movie.getVoterAverage().floatValue();
        binding.rbVoteAverage.setRating(voteAverage = voteAverage > 0 ? voteAverage / 2.0f : voteAverage);
        binding.rDate.setText(movie.getReleaseDate());
        binding.mvPop.setText(movie.getMvPop().toString());

        ImageView ivPoster;
        String imageURL;
        ivPoster = (ImageView) findViewById(R.id.ivPoster);

        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            imageURL = movie.getBackdropPath();
            Glide.with(this).load("http://via.placeholder.com/300.png").into(ivPoster);
        }else{
            imageURL = movie.getPosterPath();
            Glide.with(this).load("http://via.placeholder.com/300.png").into(ivPoster);
        }

        Glide.with(this).load(imageURL).transform(new RoundedCornersTransformation(30, 0)).into(ivPoster);


    }
}