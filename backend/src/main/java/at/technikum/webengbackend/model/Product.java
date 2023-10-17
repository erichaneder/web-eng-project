package at.technikum.webengbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;


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
    private Date upload_date;

    public Product(String name, float price, Integer amount) {
        this.name = name;
        this.price = price;
        this.upload_date = new Date();
        this.amount = amount;
    }

}
