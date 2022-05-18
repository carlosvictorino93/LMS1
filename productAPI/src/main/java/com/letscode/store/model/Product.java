package com.letscode.store.model;

import com.letscode.store.dto.ProductDTO;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;



@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "product")
public class Product {

    @MongoId
    private String id;

    private String productCode;

    private Integer quantity;

    private double price;


    public static Product convert(String id, ProductDTO dto) {
        return Product.builder()
                .id(id)
                .productCode(dto.getProductCode())
                .quantity(dto.getQuantity())
                .price(dto.getPrice())
                .build();
    }
}
