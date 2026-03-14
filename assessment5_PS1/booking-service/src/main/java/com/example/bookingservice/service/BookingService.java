package com.example.bookingservice.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookingservice.client.MovieClient;
import com.example.bookingservice.model.Booking;
import com.example.bookingservice.model.Movie;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class BookingService {

    @Autowired
    private MovieClient movieClient;

    private List<Booking> bookings = new ArrayList<>();

    public Booking createBooking(Booking booking) {

        Movie movie = getMovie(booking.getMovieId());

        int totalAmount = movie.getPrice() * booking.getTickets();
        booking.setTotalAmount(totalAmount);

        bookings.add(booking);

        return booking;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    @CircuitBreaker(name="movieService", fallbackMethod="fallbackMovie")
    public Movie getMovie(int id){
        return movieClient.getMovie(id);
    }

    public Movie fallbackMovie(int id, Throwable t){
        Movie movie = new Movie();
        movie.setId(0);
        movie.setName("Movie service unavailable");
        movie.setLanguage("NA");
        movie.setPrice(0);
        return movie;
    }
}