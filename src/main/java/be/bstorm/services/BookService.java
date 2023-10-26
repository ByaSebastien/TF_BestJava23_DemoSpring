package be.bstorm.services;

import be.bstorm.models.entities.Book;

import java.util.List;

public interface BookService {

    void create(Book book);
    List<Book> findAll();
    List<Book> findManyByTitle(String title);
    Book findById(Long id);
    void update(Long id,Book book);
    void delete(Long id);

}
