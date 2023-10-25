package be.bstorm.controllers;

import be.bstorm.models.entities.Book;
import be.bstorm.services.impl.BookServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    //L'attribut qui permettra de stocker l'instance reservée de BookServiceImpl
    //Possible grace à l'annotation @Service
    private final BookServiceImpl bookService;

    //Injection par constructeur du BookServiceImpl (Injection de dépendance)
    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/create")
    public String getCreate(Model model){
        model.addAttribute("book",new Book());
        return "book/create";
    }

    @PostMapping("/create")
    public String postCreate(@ModelAttribute Book book){
        bookService.create(book);
        return "redirect:/book";
    }

    @GetMapping
    public String findAll(Model model){
        List<Book> books = bookService.findAll();
        model.addAttribute("books",books);
        return "book/index";
    }
}
