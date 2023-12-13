package be.bstorm.services;

import be.bstorm.models.entities.Book;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecifications {

    public static Specification<Book> getByTitle(String title) {

        return (root, query, criteriaBuilder) ->
            criteriaBuilder.like(root.get("title"),"%" + title + "%");
    }
    public static Specification<Book> getByDescription(String description) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("description"),"%" + description + "%");
    }
    public static Specification<Book> getByAuthor(String author) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("author"),"%" + author + "%");
    }
}
