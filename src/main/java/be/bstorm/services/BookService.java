package be.bstorm.services;

import be.bstorm.models.entities.Book;

import java.util.List;

public interface BookService {

    void create(Book book);
    List<Book> findAll();
}
