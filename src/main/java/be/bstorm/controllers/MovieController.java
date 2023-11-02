package be.bstorm.controllers;

import be.bstorm.models.dtos.movie.MovieShortDTO;
import be.bstorm.models.entities.Movie;
import be.bstorm.models.forms.movie.MovieForm;
import be.bstorm.services.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//Annotation pour faire comprendre a Spring quececi est le controller(end points)
@Controller
//Definis que le routing pour toutes les methodes de la classe commencera par /movie
@RequestMapping("/movie")
public class MovieController {

    //Attribut pour venir stocker une instance du service
    private final MovieService movieService;

    //Injection de dependance du service(fonctionne grace a @Service)
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    //Methode qui va servir a renvoyer a l'utilisateur le formulaire de creation
    //s'appelle via la route localhost:8080/movie/create en get
    @GetMapping("/create")
    public String getCreate(Model model){
        model.addAttribute("movie",new MovieForm());
        //retourne la page html qui se trouve dans templates/movie/create.html
        //Different du routing de base
        return "movie/create.html";
    }

    //Methode qui va servir a envoyer le livre encodé dans la DB
    //s'appelle via la route localhost:8080/movie/create en post
    @PostMapping("/create")
    public String postCreate(
            @ModelAttribute MovieForm movieForm){
        Movie m = movieForm.toEntity();
        movieService.create(m);
        //redirige vers la methode get http://localhost:8080/movie
        return "redirect:/movie";
    }

    //Methode qui sert a renvoyer vers la page de la liste des films
    //Se contacte via l'url http://localhos:8080/movie en GET
    @GetMapping
    public String findAll(Model model){

        //Recupere les films existant en DB via le service
        List<Movie> movies = movieService.findAll();
        //Map tout les Movie de la liste en MovieShortDTO
        List<MovieShortDTO> dtos = movies.stream()
                .map(MovieShortDTO::fromEntity)
                .toList();
        //Stock la list des DTO pour y avoir acces dans la vue
        model.addAttribute("movies",dtos);
        //Envoie la vue templates/movie/index.html
        return "/movie/index.html";
    }
}
