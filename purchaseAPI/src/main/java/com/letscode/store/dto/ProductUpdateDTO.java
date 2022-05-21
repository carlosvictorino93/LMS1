package com.letscode.store.dto;


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
public class ProductUpdateDTO {

    @NotBlank @Length(max = 4)
    private String productCode;
    @NotNull @Positive
    private Integer quantity;
    @NotNull @Positive
    private Double price;

}
