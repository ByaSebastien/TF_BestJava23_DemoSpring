package be.bstorm.repositories;

import be.bstorm.models.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository sert rendre cette class injectable(injection de dependance)
@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    //Extends JpaRepository permet d'avoir acces a toutes ces methodes.
    //CRUD classic et plus encore
    //C'est une interface generique qui prend en 1er argument
    //Le type de l'entity qu'il doit gerer
    //Et en deuxieme le type de la colonne PK de cette entity

    List<Book> findBooksByTitleContainingIgnoreCase(String title);

    @Query("select b from Book b where upper(b.title) like %:title%")
    List<Book> searchByTitle(@Param("title") String title);

    @Query("select count(b) > 0 from Book b where b.title like :title")
    boolean existsByTitle(String title);
}
