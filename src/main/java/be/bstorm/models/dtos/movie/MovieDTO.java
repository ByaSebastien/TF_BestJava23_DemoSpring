package be.bstorm.models.dtos.movie;

import be.bstorm.models.entities.Movie;

public record MovieDTO(
        String title,
        String description) {
    public static MovieDTO fromEntity(Movie movie){
        return new MovieDTO(movie.getTitle(),movie.getDescription());
    }
}
