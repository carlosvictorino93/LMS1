package com.letscode.store.service;

import com.letscode.store.dto.ProductDTO;
import com.letscode.store.dto.ValidationProductDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    public List<ValidationProductDTO> getProduct(List<ProductDTO> productDTOS) {
        return  productDTOS
                .stream()
                .map(productDTO -> getValidationProductDTOS(productDTO.getProductCode()))
                .collect(Collectors.toList());

    }

    private ValidationProductDTO getValidationProductDTOS(String productCode) {
        WebClient webClient = WebClient.create("http://localhost:8081");
        return webClient
                .get()
                .uri("/product/validation/" + productCode)
                .retrieve()
                .bodyToMono(ValidationProductDTO.class)
                .block();
    }
}
