package be.bstorm.models.dtos.movie;

import be.bstorm.models.entities.Movie;
import lombok.Data;

public record MovieShortDTO(Long id,String title) {

    public static MovieShortDTO fromEntity(Movie movie){
        return new MovieShortDTO(movie.getId(), movie.getTitle());
    }
}
