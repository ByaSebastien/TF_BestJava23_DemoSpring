package be.bstorm.services.impl;

import be.bstorm.models.entities.Movie;
import be.bstorm.repositories.MovieRepository;
import be.bstorm.services.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

//Sert a pouvoir injecter plus tard le service
@Service
public class MovieServiceImpl implements MovieService {

    //Attribut MovieRepository pour l'injection
    private final MovieRepository movieRepository;

    //Injection de dependance de mon MovieRepository
    //Fonctionne grace à l annotation @Repository
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void create(Movie movie) {
        //Appel à la methode save de movieRepository
        //Pour persister la donnée en DB
        movieRepository.save(movie);
    }

    @Override
    public List<Movie> findAll() {
        //Appel de la methode findAll() de jpaRepository
        //Sert a renvoyer toutes mes enregistrement de movie
        return movieRepository.findAll();
    }
}
