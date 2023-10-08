package at.technikum.webengbackend.model;

/*
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
*/

import java.time.LocalDate;

//@Entity
public class Product {
    //@Id
    private Long id;
    private String name;
    private float price;
    private Integer amount;
    private LocalDate uploadDate;

    public Product() {
    }

    public Product(Long id, String name, float price,Integer amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.uploadDate = LocalDate.now();
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", uploadDate=" + uploadDate +
                '}';
    }
}
