package be.bstorm.services.impl;

import be.bstorm.models.entities.Book;
import be.bstorm.repositories.BookRepository;
import be.bstorm.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {


    //Attribut dans lequel on va stocker l'instance injectable de BookRepository
    //Fonctionne grace a l annotation @Repository
    private final BookRepository bookRepository;


    //Injection via le constructeur de l'instance de BookRepository reservé en memoire
    public  BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public void create(Book book) {
        //appel de la methode contenue dans notre BookRepository
        //Permet de creer l enregistrement en db
        bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findManyByTitle(String title) {
        return bookRepository.searchByTitle(title.toUpperCase());
    }

    @Override
    public Book findById(Long id) {
//        Optional<Book> book = bookRepository.findById(id);
//        if(book.isEmpty()){
//            throw new RuntimeException();
//        }
//        return book.get();
        return bookRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public void update(Long id, Book book) {
        Book existingBook = findById(id);
        existingBook.setTitle(book.getTitle());
        existingBook.setDescription(book.getDescription());
        bookRepository.save(existingBook);
//        book.setId(id);
//        bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
