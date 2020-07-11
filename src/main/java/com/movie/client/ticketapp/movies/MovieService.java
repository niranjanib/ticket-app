package com.movie.client.ticketapp.movies;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

@Service
public class MovieService {
    private static final String BASE_URL = "http:localhost:8080/";

    public List<Movie> getMovies () {
        Gson gson = new GsonBuilder()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        MovieInterface movieInterface =
                retrofit.create(MovieInterface.class); //explore this step
        Call<List<Movie>> moviesCall = movieInterface.getMovies();
        return responseBody(moviesCall);
    }

    public static <T> T responseBody(Call<T> call) {
        Response<T> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.body();
    }
}
