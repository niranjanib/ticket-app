package com.movie.client.ticketapp.movies;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface MovieInterface {

    @GET("movies")
    Call<List<Movie>> getMovies();
}
