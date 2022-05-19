package com.letscode.store.dto;

import com.letscode.store.model.Purchase;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePurchaseDTO {

    private LocalDateTime purchaseDate;
    private Double totalPurchased;
    private String clientName;
    private List<ProductDTO> productDTOS;


    public static ResponsePurchaseDTO convert(
            Purchase purchase, String clientName,
            List<ProductDTO> productDTOS
    ){
        return ResponsePurchaseDTO.builder()
                .purchaseDate(purchase.getPurchaseDate())
                .totalPurchased(purchase.getTotalPurchased())
                .clientName(clientName)
                .productDTOS(productDTOS)
                .build();

    }



}