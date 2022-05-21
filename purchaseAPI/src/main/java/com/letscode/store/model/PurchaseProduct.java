package com.letscode.store.model;

import com.letscode.store.dto.ValidationProductDTO;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;


@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseProduct {

    @Field("id_product")
    private String product;

    @Field(name = "quantity_purchased")
    private Integer quantityPurchased;

    public static PurchaseProduct convert(ValidationProductDTO validationProductDTO){
        return PurchaseProduct
                .builder()
                .product(validationProductDTO.getId())
                .quantityPurchased(validationProductDTO.getQuantityPurchased())
                .build();
    }
}
