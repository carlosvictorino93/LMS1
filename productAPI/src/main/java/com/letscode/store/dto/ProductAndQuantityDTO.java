package com.letscode.store.dto;

import com.letscode.store.model.Product;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductAndQuantityDTO {
    private Product product;
    private Integer quantityPurchased;
}
