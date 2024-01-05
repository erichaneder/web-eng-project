package at.technikum.webengbackend.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @Getter
    private Long id;
    @Getter
    private String name;
    @Getter
    private float price;
    @Getter
    private int quantity;
}

