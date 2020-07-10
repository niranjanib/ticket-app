package com.movie.client.ticketapp.movies;

public class Movie {
    private Long id;
    private String name;
    private String genre;

    public Movie() { }

    public Movie(Long id, String name, String genre) {
        this.id = id;
        this.name = name;
        this.genre = genre;
    }
}
