package com.example.flixster.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.MovieDetailsActivity;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    //Inflate layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("Movie Adapter", "onCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    //Involves populating the data into the item through the holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("Movie Adapter", "onBindViewHolder" + position);
        //Get the movie into position
        Movie movie = movies.get(position);
        //Bind data into the view holder
        holder.bind(movie);
    }

    //Return total count
    @Override
    public int getItemCount() {
        return movies.size();
    }

    //Not static for the parser
    //Implements View.OnClickListener
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            //itemView's OnClickListener
            itemView.setOnClickListener(this);
        }

        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());

            String imageURL;

            //Image if its landscape or portrait
            //Placeholder images when loading
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                imageURL = movie.getBackdropPath();
                Glide.with(context).load("http://via.placeholder.com/300.png").into(ivPoster);
            }else{
                imageURL = movie.getPosterPath();
                Glide.with(context).load("http://via.placeholder.com/300.png").into(ivPoster);
            }


            Glide.with(context).load(imageURL).transform(new RoundedCornersTransformation(30, 0)).into(ivPoster);
        }

        //Method generated while overriding class for parser
        @Override
        public void onClick(View view) {
            //item position
            int position = getAdapterPosition();

            //Validity of position
            if(position != RecyclerView.NO_POSITION){

                //Get movie at position
                Movie movie = movies.get(position);
                //Create intent for new activity
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                //Serialize the movie with parser
                intent.putExtra(Movie.class.getSimpleName(), Parcels.wrap(movie));

                //show activity
                context.startActivity(intent);

            }
        }
    }
}
