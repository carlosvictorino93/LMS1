package com.letscode.store.dto;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RequestPurchaseDTO {
    @CPF @NotBlank
    private String cpf;
    @NotNull
    private List<ProductDTO> productsDTO;
}
