package be.bstorm.controllers;

import be.bstorm.exceptions.UniqueBookException;
import be.bstorm.models.dtos.book.BookShortDTO;
import be.bstorm.models.entities.Book;
import be.bstorm.models.forms.book.BookForm;
import be.bstorm.services.BookService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    //L'attribut qui permettra de stocker l'instance reservée de BookServiceImpl
    //Possible grace à l'annotation @Service
    private final BookService bookService;

    //Injection par constructeur du BookServiceImpl (Injection de dépendance)
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/create")
    public String getCreate(Model model) {
        //model sert a transferer des données entre le controller et la page html
        model.addAttribute("bookForm", new BookForm());
        return "book/create.html";
    }

    @PostMapping("/create")
    public String postCreate(
            @ModelAttribute @Valid BookForm bookForm,
            BindingResult br,
            Model model) {
        if (br.hasErrors()) {
            return "book/create.html";
        }
        //Map bookForm en un Book (entity)
        Book book = bookForm.toEntity();
        //Try catch pour receptionner unique exception
        try {
            //Pour pouvoir l'envoyer a la methode create qui attend un Book(entity)
            bookService.create(book);
        } catch (UniqueBookException e) {
            model.addAttribute("uniqueException", e.getMessage());
            return "book/create.html";
        }
//        bookService.create(bookForm.toEntity());
        //Redirige vers la methode Get /book
        return "redirect:/book";
    }

    @GetMapping
    public String findAll(Model model) {
        List<Book> books = bookService.findAll();
        //On doit modifier tout les Book(entiy) en BookShortDTO
//        List<BookShortDTO> dtos = new ArrayList<>();
//        for (Book book : books){
//            BookShortDTO dto = BookShortDTO.fromEntity(book);
//            dtos.add(dto);
//        }
        //Avec stream
        List<BookShortDTO> dtos = books.stream()
                .map(b -> BookShortDTO.fromEntity(b))
                .toList();
        model.addAttribute("dtos", dtos);
        return "book/index.html";
    }

    @GetMapping("/{id}")
    public String findOneById(
            @PathVariable Long id,
            Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        return "book/detail.html";
    }

    @GetMapping("/update/{id}")
    public String getUpdate(
            @PathVariable Long id,
            Model model) {
        Book book = bookService.findById(id);
        //Map mon Book(entity) en un BookForm
        //Pour pouvoir l'envoyer dans la vue via Model
        BookForm bookForm = BookForm.fromEntity(book);
        //Vu que BookForm n a pas d'id je l'envoie tel quel dans la vue via model
        model.addAttribute("id", id);
        model.addAttribute("bookForm", bookForm);
        return "book/update.html";
    }

    @PostMapping("update/{id}")
    public String postUpdate(
            @ModelAttribute @Valid BookForm bookForm,
            BindingResult br,
            @PathVariable Long id,
            Model model) {
        if (br.hasErrors()) {
            model.addAttribute("id", id);
            model.addAttribute("bookForm", bookForm);
            return "book/update.html";
        }
        //Map mon BookForm en un Book(entity)
        Book book = bookForm.toEntity();
        try {
            //Pour pouvoir l'envoyer a la methode update qui attend un Book(entity)
            bookService.update(id, book);
        } catch (UniqueBookException e) {
            model.addAttribute("uniqueException", e.getMessage());
            return "book/update.html";
        }
        return "redirect:/book";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/book";
    }

    @PostMapping("/search")
    public String search(
            @RequestParam("input") String input,
            Model model) {
        List<Book> books = bookService.findManyByTitle(input);
        List<BookShortDTO> dtos = books.stream()
                .map(BookShortDTO::fromEntity)
                .toList();
        model.addAttribute("dtos", dtos);
        return "book/index.html";
    }
}
