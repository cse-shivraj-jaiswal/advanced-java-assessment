package com.example.movieservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.movieservice.model.Movie;
import com.example.movieservice.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService service;

    @GetMapping
    public List<Movie> getMovies(){
        return service.getMovies();
    }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable int id){
        return service.getMovieById(id);
    }

}