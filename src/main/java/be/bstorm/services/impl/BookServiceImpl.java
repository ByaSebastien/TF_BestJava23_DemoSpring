package be.bstorm.services.impl;

import be.bstorm.models.entities.Book;
import be.bstorm.repositories.BookRepository;
import be.bstorm.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
