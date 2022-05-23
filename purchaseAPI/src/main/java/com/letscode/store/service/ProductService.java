package com.letscode.store.service;

import com.letscode.store.dto.ProductDTO;
import com.letscode.store.dto.ProductUpdateDTO;
import com.letscode.store.dto.TokenDTO;
import com.letscode.store.dto.ValidationProductDTO;
import com.letscode.store.exception.NotEnoughException;
import com.letscode.store.exception.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    public List<ValidationProductDTO> getProduct(List<ProductDTO> productDTOS, String token) {
        return productDTOS
                .stream()
                .map(productDTO -> getValidationProductDTOS(productDTO.getProductCode(), productDTO.getQuantityPurchased(), token))
                .collect(Collectors.toList());

    }

    private ValidationProductDTO getValidationProductDTOS(String productCode, Integer quantityPurchased, String token) {

        WebClient webClient = WebClient.create("http://localhost:8082");
        try {
            ValidationProductDTO validationProductDTO = webClient
                    .get()
                    .uri("/product/validation/" + productCode)
                    .header("authorization", "Bearer " + token)
                    .retrieve()
                    .bodyToMono(ValidationProductDTO.class)
                    .doOnError(throwable -> System.out.println(throwable.getMessage()))
                    .block();
            if (validationProductDTO != null)
                if (validationProductDTO.getQuantity() > quantityPurchased)
                    validationProductDTO.setQuantityPurchased(quantityPurchased);
                else throw new NotEnoughException("Não há quantidade suficiente do produto " + productCode + "!");
            return validationProductDTO;
        } catch (WebClientResponseException e) {
//            System.out.print(e);
            throw new NotFoundException("Produto " + productCode + " não encontrado!");
        }

    }

    public void updateProduct(List<ValidationProductDTO> productDTOS, String token) {
        WebClient webClient = WebClient.create("http://localhost:8082");
        productDTOS.forEach(validationProductDTO -> {
            double quantity = validationProductDTO.getQuantity() - validationProductDTO.getQuantityPurchased();
            String body = "{\n" +
                    "\"quantity\": " + quantity + ",\n" +
                    "\"productCode\": \"" + validationProductDTO.getProductCode() + "\",\n" +
                    "\"price\": " + validationProductDTO.getPrice() + "\n" +
                    "}";

//            String body = "{\n" +
//                    "\"username\": \"userv\", " +
//                    "\"password\": \"userv\"\n" +
//                    "}";
//            ProductUpdateDTO productUpdateDTO = ProductUpdateDTO
//                    .builder()
//                    .quantity(validationProductDTO.getQuantity())
//                    .price(validationProductDTO.getPrice())
//                    .productCode(validationProductDTO.getProductCode())
//                    .build();

            try {
                ProductDTO productDTO = webClient
                        .patch()
                        .uri("/product/" + validationProductDTO.getProductCode())
                        .header("authorization", "Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(body))
                        .retrieve()
                        .bodyToMono(ProductDTO.class)
                        .block();
            } catch (WebClientResponseException e) {
                            System.out.print(e);
                throw new NotFoundException("Erro na atualização, compra não concluída!");
            }
        });
    }


//    public List<ValidationProductDTO> setResponseProduct(
//            List<ProductDTO> productDTOS,
//            List<ValidationProductDTO> validationProductDTOS
//    ) {
////        List<ResponseProductDTO> responseProductDTOs = new ArrayList<>();
//
//        return validationProductDTOS
//                .stream()
//                .peek(validationProductDTO -> validationProductDTO.setQuantityPurchased(getQuantity(productDTOS, validationProductDTO)))
//                .collect(Collectors.toList());
//
////        return responseProductDTOs;
//    }

//    private Integer getQuantity(List<ProductDTO> productDTOS, ValidationProductDTO validationProductDTO) {
//        return productDTOS
//                .stream()
//                .filter(productDTO -> Objects.equals(productDTO.getProductCode(), validationProductDTO.getProductCode()))
//                .findFirst()
//                .get()
//                .getQuantityPurchased();
//    }
}
