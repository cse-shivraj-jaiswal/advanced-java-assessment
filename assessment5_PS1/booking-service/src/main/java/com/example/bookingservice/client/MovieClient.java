package com.example.bookingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.bookingservice.model.Movie;

@FeignClient(name = "movie-service")
public interface MovieClient {

    @GetMapping("/movies/{id}")
    Movie getMovie(@PathVariable("id") int id);

}