package com.letscode.store.dto;

import com.letscode.store.model.Product;
import lombok.*;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidationProductDTO {
    private String id;
    private String productCode;
    private Integer quantity;
    private double price;
    private Integer quantityPurchased;

    public static ValidationProductDTO convert(Product product){
        return ValidationProductDTO
                .builder()
                .id(product.getId())
                .productCode(product.getProductCode())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .quantityPurchased(0)
                .build();
    }
}
