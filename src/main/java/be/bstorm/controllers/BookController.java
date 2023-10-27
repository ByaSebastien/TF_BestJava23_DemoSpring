package be.bstorm.controllers;

import be.bstorm.models.entities.Book;
import be.bstorm.services.impl.BookServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
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
        //model sert a transferer des données entre le controller et la page html
        model.addAttribute("book",new Book());
        return "book/create.html";
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
        return "book/index.html";
    }

    @GetMapping("/{id}")
    public String findOneById(
            @PathVariable Long id,
            Model model){
        Book book = bookService.findById(id);
        model.addAttribute("book",book);
        return "book/detail.html";
    }

    @GetMapping("/update/{id}")
    public String getUpdate(
            @PathVariable Long id,
            Model model){
        Book book = bookService.findById(id);
        model.addAttribute("id",id);
        model.addAttribute("book",book);
        return "book/update.html";
    }

    @PostMapping("update/{id}")
    public String postUpdate(
            @ModelAttribute Book book,
            @PathVariable Long id){
        bookService.update(id,book);
        return "redirect:/book";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        bookService.delete(id);
        return "redirect:/book";
    }

    @PostMapping("/search")
    public String search(
            @RequestParam("input") String input,
            Model model){
        List<Book> books = bookService.findManyByTitle(input);
        model.addAttribute("books",books);
        return "book/index.html";
    }
}
