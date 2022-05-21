//package com.letscode.store.service;
//
//import com.letscode.store.dto.ProductAndQuantityDTO;
//import com.letscode.store.model.Purchase;
//import com.letscode.store.model.PurchaseProduct;
//import com.letscode.store.model.PurchasedProductKey;
//import com.letscode.store.repository.PurchaseProductRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RequiredArgsConstructor
//@Service
//public class PurchaseProductService {
//
//    private final PurchaseProductRepository purchaseProductRepository;
//
//    public List<PurchaseProduct> savePurchaseProduct(Purchase purchase, List<ProductAndQuantityDTO> productAndQuantityDTOS) {
//        List<PurchaseProduct> purchaseProducts = new ArrayList<>();
//        productAndQuantityDTOS.forEach(productAndQuantityDTO -> {
//            PurchasedProductKey key = PurchasedProductKey.builder()
//                    .idProduct(productAndQuantityDTO.getProduct().getId())
//                    .idPurchase(purchase.getId())
//                    .build();
//
//            PurchaseProduct purchaseProduct = PurchaseProduct.builder()
//                    .product(productAndQuantityDTO.getProduct())
//                    .purchase(purchase)
//                    .purchasedProductKey(key)
//                    .quantityPurchased(productAndQuantityDTO.getQuantityPurchased())
//                    .build();
//            purchaseProducts.add(purchaseProduct);
//        });
//
//        return purchaseProductRepository.saveAll(purchaseProducts);
//    }
//}
