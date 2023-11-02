package be.bstorm.controllers;

import be.bstorm.models.dtos.movie.MovieDTO;
import be.bstorm.models.dtos.movie.MovieShortDTO;
import be.bstorm.models.entities.Movie;
import be.bstorm.models.forms.movie.MovieForm;
import be.bstorm.repositories.MovieRepository;
import be.bstorm.services.MovieService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Annotation pour faire comprendre a Spring quececi est le controller(end points)
@Controller
//Definis que le routing pour toutes les methodes de la classe commencera par /movie
@RequestMapping("/movie")
public class MovieController {

    //Attribut pour venir stocker une instance du service
    private final MovieService movieService;
    private final MovieRepository movieRepository;

    //Injection de dependance du service(fonctionne grace a @Service)
    public MovieController(MovieService movieService,
                           MovieRepository movieRepository) {
        this.movieService = movieService;
        this.movieRepository = movieRepository;
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

    //Methode qui sert a renvoyer vers lapage detail d'un livre
    //Se contacte via la route http://localhost:8080/movie/detail/?  (? etant un Long id) en GET
    @GetMapping("/detail/{id}")
    public String detail(
            Model model,
            @PathVariable Long id
    ){
        //Contacter mon service pour recuperer un film
        Movie movie = movieService.findById(id);
        //Mapper le film en dto
        MovieDTO dto = MovieDTO.fromEntity(movie);
        //Stocker le film dans model pour y avoir acces dans la vue
        model.addAttribute("movie",dto);
        //Envoie vers la vue templates/movie/detail.html
        return "movie/detail.html";
    }

    //Methode qui sert a renvoyer vers la page update d'un livre
    //Se contacte via la route http://localhost:8080/movie/update/?  (? etant un Long id) en GET
    @GetMapping("/update/{id}")
    public String getUpdate(
            @PathVariable Long id,
            Model model
    ){
        //Recupere un film via Id en contactant le service
        Movie movie = movieService.findById(id);
        //Transforme le film en MovieForm pour envoyer a la vue
        MovieForm form = MovieForm.fromEntity(movie);
        //Stock le film dans model pour que la vue y ai acces
        model.addAttribute("id",id);
        model.addAttribute("movie",form);
        //Envoie vers la page templates/movie/update.html
        return "movie/update.html";
    }

    //Methode qui sert a soumettre le formulaire update d'un livre
    //Se contacte via la route http://localhost:8080/movie/update/?  (? etant un Long id) en POST
    @PostMapping("update/{id}")
    public String postUpdate(
            @PathVariable Long id,
            @ModelAttribute MovieForm form
    ){
        //Map le formulaire en movie entity pour pourvoir le persister
        Movie movie = form.toEntity();
        //Persister le film en db
        movieService.update(id,movie);
        //redirige vers la methode en GET sur l'url http://localhost:8080/movie
        return "redirect:/movie";
    }

    //Methode qui sert a supprimer un livre
    //Se contacte via la route http://localhost:8080/movie/delete/?  (? etant un Long id) en GET
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        //appel de la methode delete du service
        movieService.delete(id);
        //redirige vers la methode en GET sur l'url http://localhost:8080/movie
        return "redirect:/movie";
    }
}
