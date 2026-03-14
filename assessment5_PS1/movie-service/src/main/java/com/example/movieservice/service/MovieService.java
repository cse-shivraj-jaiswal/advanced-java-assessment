package com.example.movieservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.movieservice.model.Movie;

@Service
public class MovieService {

    private List<Movie> movies = List.of(
            new Movie(1,"Inception","English",250),
            new Movie(2,"Avatar","English",300)
    );

    public List<Movie> getMovies(){
        return movies;
    }

    public Movie getMovieById(int id){
        return movies.stream()
                .filter(m -> m.getId()==id)
                .findFirst()
                .orElse(null);
    }

}