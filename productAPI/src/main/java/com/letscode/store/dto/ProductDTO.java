package com.letscode.store.dto;


import com.letscode.store.model.Product;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    @NotBlank @Length(max = 4)
    private String productCode;
    @NotNull @Positive
    private Integer quantity;
    @NotNull @Positive
    private Double price;

    public static ProductDTO convert(Product product) {
        return ProductDTO.builder()
                .productCode(product.getProductCode())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .build();
    }
    public static ProductDTO convert(ProductAndQuantityDTO product) {
        return ProductDTO.builder()
                .productCode(product.getProduct().getProductCode())
                .quantity(product.getQuantityPurchased())
                .price(product.getProduct().getPrice())
                .build();
    }
}
