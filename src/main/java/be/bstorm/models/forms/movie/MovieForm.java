package be.bstorm.models.forms.movie;

import be.bstorm.models.entities.Movie;
import lombok.Data;

@Data
public class MovieForm {

    private String title;
    private String description;

    public Movie toEntity(){
        Movie m = new Movie();
        m.setTitle(this.title);
        m.setDescription(this.description);
        return m;
    }

    public static MovieForm fromEntity(Movie movie){
        MovieForm form = new MovieForm();
        form.setTitle(movie.getTitle());
        form.setDescription(movie.getDescription());
        return form;
    }
}
