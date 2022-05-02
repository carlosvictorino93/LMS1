package com.letscode.store.dto;

import com.letscode.store.model.Purchase;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePurchaseDTO {

    private LocalDateTime purchaseDate;
    private Double totalPurchased;
    private ClientDTO clientDTO;
    private List<ProductDTO> productDTOS;


    public static ResponsePurchaseDTO convert(Purchase purchase){
        return ResponsePurchaseDTO.builder()
                .purchaseDate(purchase.getPurchaseDate())
                .totalPurchased(purchase.getTotalPurchased())
                .clientDTO(ClientDTO.convert(purchase.getClient()))
                .productDTOS(purchase.getPurchaseProducts().stream().map(ProductDTO::convert).collect(Collectors.toList()))
                .build();

    }

    public static ResponsePurchaseDTO convert(Purchase purchase, List<ProductAndQuantityDTO> purchasedProducts){
        return ResponsePurchaseDTO.builder()
                .purchaseDate(purchase.getPurchaseDate())
                .totalPurchased(purchase.getTotalPurchased())
                .clientDTO(ClientDTO.convert(purchase.getClient()))
                .productDTOS(purchasedProducts.stream().map(ProductDTO::convert).collect(Collectors.toList()))
                .build();
    }


}