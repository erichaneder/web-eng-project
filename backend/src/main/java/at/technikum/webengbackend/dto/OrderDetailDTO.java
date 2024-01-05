package at.technikum.webengbackend.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDetailDTO {
    private Long id;
    private ProductDTO product;
    private Integer quantity;
}