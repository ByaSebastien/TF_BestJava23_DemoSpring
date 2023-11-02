package be.bstorm.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Movie {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100,unique = true,nullable = false)
    private String title;

    private String description;
}
