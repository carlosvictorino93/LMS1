package com.letscode.store.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ValidationProductDTO {
    private String id;
    private String productCode;
    private Integer quantity;
    private double price;
}
