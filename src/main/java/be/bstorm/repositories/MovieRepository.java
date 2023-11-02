package be.bstorm.repositories;

import be.bstorm.models.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Sert a pouvoir injecter plus tard le repo
//JpaRepository contient toutes les methode d'un crud classique et plus encore
//Pas besoin d'implementer une classe le framework s'en occupe
@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
}
