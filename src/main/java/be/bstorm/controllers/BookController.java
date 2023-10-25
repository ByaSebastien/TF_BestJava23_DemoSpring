package be.bstorm.controllers;

import be.bstorm.models.entities.Book;
import be.bstorm.services.impl.BookServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    //L'attribut qui permettra de stocker l'instance reservée de BookServiceImpl
    //Possible grace à l'annotation @Service
    private final BookServiceImpl bookService;

    //Injection par constructeur du BookServiceImpl (Injection de dépendance)
    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book/create")
    public String getCreate(){

        return "book/create";
    }
}
