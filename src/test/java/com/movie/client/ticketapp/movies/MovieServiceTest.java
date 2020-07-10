package com.movie.client.ticketapp.movies;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieServiceTest {

    @Test
    void shouldFetchMovies() {
        MovieService movieService = new MovieService();

        List<Movie> movies = movieService.getMovies();

        assertEquals(movies.size(), 3);
    }
}
