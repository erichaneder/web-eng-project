package at.technikum.webengbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Builder
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "orderNo is mandatory")
    private String orderNo;

    @NotNull(message = "Total Amount is mandatory")
    private float totalAmount;

    private int totalQuantity;

    @CreationTimestamp
    private Instant createdOn;

    @UpdateTimestamp
    private Instant lastUpdatedOn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;  // Link to the User who made the order

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails; // Link to the products in the order
}
