package com.letscode.store.model;

import com.letscode.store.dto.ProductDTO;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_code")
    private String productCode;

    private Integer quantity;

    private double price;


    public static Product convert(ProductDTO dto) {
        return Product.builder()
                .productCode(dto.getProductCode())
                .quantity(dto.getQuantity())
                .price(dto.getPrice())
                .build();
    }
}
