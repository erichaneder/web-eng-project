package at.technikum.webengbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;


@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private float price;
    private Integer amount;
    private LocalDate uploadDate;

    public Product(Long id, String name, float price,Integer amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.uploadDate = LocalDate.now();
        this.amount = amount;
    }

}
