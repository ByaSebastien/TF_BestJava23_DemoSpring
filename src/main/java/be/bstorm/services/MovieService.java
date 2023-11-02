package be.bstorm.services;

import be.bstorm.models.entities.Movie;

import java.util.List;

public interface MovieService {

    void create(Movie movie);

    List<Movie> findAll();
}
