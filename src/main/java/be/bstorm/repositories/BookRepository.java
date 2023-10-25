package be.bstorm.repositories;

import be.bstorm.models.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
//TODO @Repo
public interface BookRepository extends JpaRepository<Book,Long> {
    
//    Optional<Book> findByTitle(String title);
}
