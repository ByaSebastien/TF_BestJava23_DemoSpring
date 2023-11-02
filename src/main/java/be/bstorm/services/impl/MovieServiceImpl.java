package be.bstorm.services.impl;

import be.bstorm.models.entities.Movie;
import be.bstorm.repositories.MovieRepository;
import be.bstorm.services.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Movie findById(Long id) {
        //Appel de la methode findById de jpaRepository
        //sert a renvoyer un movie (si il y en a un) via son Id
        //Renvoi un Optional<Movie> etant donné que nous ne sommes pas sure d'en avoir un
        //Sur un optional ou gere le fait de ne rien avoir avec .orElseThrow() qui renvoi NoSuchElementException
        return movieRepository.findById(id).orElseThrow();


        //Autre methode (déconseillée)
//        Optional<Movie> movie = movieRepository.findById(id);
//        if(movie.isEmpty()){
//            throw new RuntimeException();
//        }
//        return movie.get();
    }

    @Override
    public void update(Long id, Movie movie) {
        //Recuperer un film depuis la methode de ce service
        //Qui gere deja l'optional
        Movie existingMovie = findById(id);
        //Ecraser les valeurs de l'ancien film par les nouvelles
        existingMovie.setTitle(movie.getTitle());
        existingMovie.setDescription(movie.getDescription());
        //Enregistrer les changement en DB
        movieRepository.save(existingMovie);
    }

    @Override
    public void delete(Long id) {
        //Supprimer un film via son Id avec la methode deleteById de jpaRepository
        movieRepository.deleteById(id);
    }
}
