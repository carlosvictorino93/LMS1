package com.letscode.store.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestPurchaseProductDTO {
    @NotBlank
    private String productCode;
    @NotNull
    @Positive
    private Integer quantityPurchased;
}
