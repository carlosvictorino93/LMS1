package com.letscode.store.service;


import com.letscode.store.dto.*;
import com.letscode.store.exception.NotEnoughException;

import com.letscode.store.model.Purchase;
import com.letscode.store.model.PurchaseProduct;
import com.letscode.store.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ClientService clientService;
    private final ProductService productService;
    private final TokenService tokenService;

    public ResponsePurchaseDTO savePurchase(RequestPurchaseDTO requestPurchaseDTO) {

        TokenDTO token = tokenService.getToken();

        ValidationClientDTO client = clientService.getClient(requestPurchaseDTO.getCpf(), token.getToken());

        List<ValidationProductDTO> productDTOS = productService.getProduct(requestPurchaseDTO.getProductsDTO(), token.getToken());

        Double totalPurchased = getTotalPurchased(productDTOS);

        return getResponsePurchaseDTO(token, client, productDTOS, totalPurchased);
    }

    @Transactional
    ResponsePurchaseDTO getResponsePurchaseDTO(TokenDTO token, ValidationClientDTO client, List<ValidationProductDTO> productDTOS, Double totalPurchased) {
        Purchase purchase = purchaseRepository.save(
                Purchase.builder()
                        .purchaseDate(LocalDateTime.now())
                        .totalPurchased(totalPurchased)
                        .client(client.getId())
                        .products(productDTOS.stream().map(PurchaseProduct::convert).collect(Collectors.toList()))
                        .build()
        );

        List<ResponseProductDTO> responseProductDTOs = productDTOS.stream().map(ResponseProductDTO::convert).collect(Collectors.toList());
        productService.updateProduct(productDTOS, token.getToken());
//
//        purchaseProductService.savePurchaseProduct(purchase, productAndQuantityDTOS);

        return ResponsePurchaseDTO.convert(purchase, client.getName(), responseProductDTOs);
    }

//    private List<ProductAndQuantityDTO> getProductAndQuantityDTOS(RequestPurchaseDTO requestPurchaseDTO) {
//        return requestPurchaseDTO
//                .getPurchasedProducts()
//                .stream()
//                .map(requestPurchaseProductDTO -> {
//                    Product product = productService
//                            .getProduct(requestPurchaseProductDTO.getProductCode());
//                    if (product.getQuantity() < requestPurchaseProductDTO.getQuantityPurchased()){
//                        throw new NotEnoughException("Not enough of product with code " + product.getProductCode());
//                    }
//                    product.setQuantity(product.getQuantity() - requestPurchaseProductDTO.getQuantityPurchased());
//                    return ProductAndQuantityDTO.builder()
//                            .product(product)
//                            .quantityPurchased(requestPurchaseProductDTO.getQuantityPurchased())
//                            .build();
//                })
//                .collect(Collectors.toList());
//    }

    private Double getTotalPurchased(List<ValidationProductDTO> validationProductDTOS) {
        AtomicReference<Double> returnTotal = new AtomicReference<>(0D);
        validationProductDTOS.forEach(validationProductDTO -> returnTotal.updateAndGet(v ->
                v + validationProductDTO.getPrice()
                * validationProductDTO.getQuantityPurchased()));
        return returnTotal.get();
    }

//    public Page<ResponsePurchaseDTO> listPurchase(RequestPurchaseDTO predicate, Pageable pageable) {
//
//                Page<Purchase> purchasePage = purchaseRepository.findAll(predicate, pageable);
//        return purchasePage.map(ResponsePurchaseDTO::convert);
//    }
}
