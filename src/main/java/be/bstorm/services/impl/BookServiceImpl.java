package be.bstorm.services.impl;

import be.bstorm.exceptions.UniqueBookException;
import be.bstorm.models.entities.Author;
import be.bstorm.models.entities.Book;
import be.bstorm.repositories.AuthorRepository;
import be.bstorm.repositories.BookRepository;
import be.bstorm.services.BookService;
import be.bstorm.services.BookSpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {


    //Attribut dans lequel on va stocker l'instance injectable de BookRepository
    //Fonctionne grace a l annotation @Repository
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;


    //Injection via le constructeur de l'instance de BookRepository reservé en memoire
    public  BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Transactional
    @Override
    public void create(Book book) {
        //version sans author
//        if(bookRepository.existsByTitle(book.getTitle())){
//            throw new UniqueBookException();
//        }
//        //appel de la methode contenue dans notre BookRepository
//        //Permet de creer l enregistrement en db
//        bookRepository.save(book);

        //Demo transaction
        Author author = authorRepository.save(book.getAuthor());
        book.setAuthor(author);
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
        if(bookRepository.existsByTitle(book.getTitle())){
            throw new UniqueBookException();
        }
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

    @Override
    public List<Book> getByCriteria(String title, String description) {

        Specification<Book> spec = Specification.where(null);
        if(title != null){
            spec = spec.and(BookSpecifications.getByTitle(title));
        }
        if(description != null){
            spec = spec.and(BookSpecifications.getByDescription(description));
        }
        return bookRepository.findAll(spec);
    }
}
