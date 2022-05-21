package com.letscode.store.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductDTO {

    private String productCode;
    private Double price;
    private Integer quantityPurchased;


    public static ResponseProductDTO convert(ValidationProductDTO validationProductDTO) {
        return ResponseProductDTO.builder()
                .productCode(validationProductDTO.getProductCode())
                .price(validationProductDTO.getPrice())
                .quantityPurchased(validationProductDTO.getQuantityPurchased())
                .build();
    }
}